package cn.water.cf.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.water.cf.dao.IArticleDao;
import cn.water.cf.domain.Article;
import cn.water.cf.domain.sns.Event;
import cn.water.cf.domain.sns.EventType;
import cn.water.cf.service.IArticleService;
import cn.water.cf.service.IEventService;
import cn.water.cf.service.IUserService;
import cn.water.cf.utils.HTMLUtil;
import cn.water.cf.utils.PageUtil;
import cn.water.cf.utils.lucene.ArticleLucene;
import cn.water.cf.web.form.Page;
import cn.water.cf.web.form.Result;
@Service(IArticleService.SERVICE_NAME)
@Transactional(readOnly=true)
public class ArticleServiceImpl implements IArticleService{
	
	@Resource(name=IArticleDao.SERVICE_NAME)
	private IArticleDao articleDao;
	
	@Resource(name=IEventService.SERVICE_NAME)
	private IEventService eventService;
	
	@Resource(name=IUserService.SERVICE_NAME)
	private IUserService userService;
	
	/**  
	* @Name: addArticle
	* @Description: 保存文章对象
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-25 （创建日期）
	* @Parameters: Article 文章对象
	* @Return: null
	* 业务逻辑
	* 	1，校验传递的对象、
	*   2，保存文章对象
	*   3，保存用户操作记录
	*   4，对文章进行全文索引操作
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void addArticle(Article article) {
		
		if(article == null){
			throw new RuntimeException("文章对象不能为空！");
		}
		if(article.getOwner().getId()==null && "".equals(article.getOwner().getId())){
			throw new RuntimeException("用户对象不能为空，获取id字段不能为空");
		}
		//提取content的一部分内容最为summary，并删除其中的html标签
		String summary = content2summary(article.getContent());
		article.setSummary(summary);
		//调用dao层的方法保存文章对象
		articleDao.save(article);
		//保存用户的操作记录
		Event event = new Event();
		event.setCreateTime(article.getCreateDate());
		event.setOwner(article.getOwner());
		event.setMessage_type(EventType.SEND_BLOG);
		event.setEventMsg(article.getSummary());
		
		
		//查询用户的所有好友
		String[] friends = userService.findFriendsById(article.getOwner().getId());
		if(friends !=null && friends.length>0){
			//将数组对象转换为Set对象 
			Set<String> friendSet = new HashSet<String>(Arrays.asList(friends));
			//调用event业务层的方法进行保存
			eventService.addEvent(event, friendSet);
		} else{
			eventService.addEvent(event);
		}
		
		//给该文章创建索引
		ArticleLucene articleLucene = ArticleLucene.newInstance();
		articleLucene.createArticleIndex(article);
	}
	
	/**  
	* @Name: content2summary
	* @Description: 提取content中的内容转换为摘要内容，并取出其中的标签
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-30 （创建日期）
	* @Parameters: String 文章内容
	* @Return: String 文章的摘要
	*/
	private String content2summary(String content) {
		//如果内容不为空执行以下的方法
		String summary = "";
		if(content !=null && content.length()>0){
			//调用HTMLUtil方法将html转换为纯文本格式的文字
			summary = HTMLUtil.Html2Text(content);
			//判断content的长度是否大于150
			if(summary.length()>=150){
				//截取0到150个字符
				summary = summary.substring(0, 150);
			}
			//去掉空格
			summary = HTMLUtil.replaceBlank(summary);
			//去掉html的实体
			summary = HTMLUtil.replaceHtmlEntry(summary);
		}
		return summary;
	}
	/**  
	* @Name: findAllArticleByUserId
	* @Description: 根据用户的id查询用户的所有博文
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-01（创建日期）
	* @Parameters: String userId 用户id
	* @Return: List<Article> 文章集合对象
	*/
	public List<Article> findAllArticleByUserId(String userId) {
		if(StringUtils.isNotBlank(userId)){
			String condition = " and o.owner.id = ? ";
			Object[] params = {userId};
			LinkedHashMap<String,String> hashMap = new LinkedHashMap<String, String>();
			List<Article> articleList = articleDao.findCollectionByConditionNoPage(condition, params, hashMap);
			return articleList;
		}
		return null;
		
	}
	/**  
	* @Name: showUserArticleByPage
	* @Description: 分页查询用户文章
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-01（创建日期）
	* @Parameters: String userId 用户id Page 分页对象
	* @Return: List<Article> 文章集合对象
	*/
	public Result showUserArticleByPage(String userId, Page page) {
		//调用分页的工具类来转换原来page的参数
		page = PageUtil.createPage(page,articleDao.queryUserAllArticleCount(userId));
		List<Article> all = articleDao.queryByPage(userId, page);
		Result result = new Result();
		result.setPage(page);
		result.setList(all);
		return result;


	}
	/**  
	* @Name: findArticleById
	* @Description: 根据用户id查找一篇文章
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-01（创建日期）
	* @Parameters: String article_id 文章id
	* @Return: Article 文章对象
	*/
	public Article findArticleById(String article_id,boolean isUpdateAccess) {
		if(StringUtils.isNotBlank(article_id)){
			//article的id字段为int整型
			Article article = articleDao.findEntityById(Integer.parseInt(article_id));
			if(isUpdateAccess){
				List<Object>keys = new ArrayList<Object>();
				List<Object>params = new ArrayList<Object>();
				LinkedHashMap<String,Object> conditions = new LinkedHashMap<String, Object>();
				//组织更新的条件
				keys.add("accessNumber");
				params.add(article.getAccessNumber()+1);
				conditions.put("article_id", article_id);
				//调用dao层的方法进行局部的更新
				articleDao.partialRenewal(keys, params, conditions);
			}
			return article;
		}
		return null;
	}
	/**  
	* @Name: delArticleById
	* @Description: 根据用户id删除一篇文章
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-03（创建日期）
	* @Parameters: String article_id 文章id
	* @Return: null
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void delArticleById(String article_id) {
		if(StringUtils.isNotBlank(article_id)){
			//id字段类型转换
			int id = Integer.parseInt(article_id);
			articleDao.deleteById(id);
		}
		
	}
	/**  
	* @Name: delArticleById
	* @Description: 更新一篇文章
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-04（创建日期）
	* @Parameters: String article_id 文章id
	* @Return: null
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void updateArticle(Article article) {
		if(article == null) {
			throw new RuntimeException("article 不能为空");
		}
		//定义更新的参数
		List<Object> keys = new ArrayList<Object>();
		List<Object> params = new ArrayList<Object>();
		LinkedHashMap<String, Object> conditions = new LinkedHashMap<String, Object>();
		//如果内容不为空，则提取出summary,同时保存两个字段
		if(article.getContent() != null && !"".equals(article.getContent())){
			//TODO 
			String summary = content2summary(article.getContent());
			keys.add("summary");
			keys.add("content");
			
			params.add(summary);
			params.add(article.getContent());
		}
		//如果标题内容不为空
		if(article.getTitle() != null){
			keys.add("title");
			params.add(article.getTitle());
		}
		//设置更新的时间
		if(article.getUpdateDate() != null){
			keys.add("updateDate");
			params.add(article.getUpdateDate());
		}
		
		keys.add("type");
		params.add(article.getType());
		keys.add("keywords");
		params.add(article.getKeywords());
		
		conditions.put("article_id", article.getArticle_id());
		
		//调用局部的更新方法
		articleDao.partialRenewal(keys, params, conditions);
		//articleDao.update(article);
		
	}

}
