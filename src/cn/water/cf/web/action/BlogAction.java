package cn.water.cf.web.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.water.cf.domain.Article;
import cn.water.cf.domain.KeyWord;
import cn.water.cf.domain.SystemDDL;
import cn.water.cf.domain.Type;
import cn.water.cf.domain.User;
import cn.water.cf.domain.blog.album.Album;
import cn.water.cf.service.IAlbumService;
import cn.water.cf.service.IArticleService;
import cn.water.cf.service.IKeyWordService;
import cn.water.cf.service.ITypeService;
import cn.water.cf.utils.SessionUtil;
import cn.water.cf.web.form.Page;
import cn.water.cf.web.form.Result;

import com.opensymphony.xwork2.ActionContext;

@Controller("blogAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class BlogAction extends BaseAction<Article>{
	
	//创建类型对象的业务层对象
	@Resource(name=ITypeService.SERVICE_NAME)
	private ITypeService typeService;
	//创建文章对象的业务层对象
	@Resource(name=IArticleService.SERVICE_NAME)
	private IArticleService articleService;
	//创建KeyWord对象的业务层对象
	@Resource(name=IKeyWordService.SERVICE_NAME)
	private IKeyWordService keyWordService;
	@Resource(name=IAlbumService.SERVICE_NAME)
	private IAlbumService albumService;
	
	/**  
	* @Name: home
	* @Description:跳转到博客主页面
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-27（创建日期）
	* @Reture : home /WEB-INF/page/blogindex.jsp
	*/
	@SuppressWarnings("unchecked")
	public String home(){
		//获取用户的id字段
		User user = SessionUtil.getGlobelUser(request);
		String userId = user.getId();
		//version 1获取用户的所有文章,并添加上request中返回给客户端
		//List<Article> articleList = articleService.findAllArticleByUserId(userId);
		//version 2根据分页对象来获取文章对象
		//通过调用业务逻辑组件来完成查询
		String currentPage = request.getParameter("currentPage");
		int currentPageInt = 1;
		if(currentPage != null && currentPage != ""){
			currentPageInt = Integer.parseInt(currentPage);
		}
		Page page = new Page();
		page.setCurrentPage(currentPageInt);
		page.setEveryPage(10);
		
		Result result = articleService.showUserArticleByPage(userId, page);
		page = result.getPage();
		List<Article> articleList = result.getList();
		//将分页和啊文章集合对象添加到了request中返回给客户端
		request.setAttribute("articleList", articleList);
		request.setAttribute("page", page);
		return "home";
	}
	/**  
	* @Name: writeBlogUI
	* @Description:跳转到发表博文的页面
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-27（创建日期）
	* @Reture : home /WEB-INF/page/writeBlog.jsp
	*/
	public String writeBlogUI(){
		//获取所有的类型对象，并保存在reuqest中
		List<Type> types = typeService.findAllType();
		request.setAttribute("types", types);
		//获取所有的keyword对象，并保存到request中
		List<KeyWord> keyWords = keyWordService.getAllKeyWord();
		request.setAttribute("keyWords", keyWords);
		return "writeBlogUI";
	}
	/**  
	* @Name: addBlog
	* @Description:保存博客文章
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-27（创建日期）
	* @Reture : returnHome
	*/
	public String addBlog(){
		//设置博文上传的时间
		this.valueObject.setCreateDate(new Date());
		//设置博文更新时间跟上传的时间一致
		this.valueObject.setUpdateDate(new Date());
		//设置文章的所所属者的id
		User user = SessionUtil.getGlobelUser(request);
		this.valueObject.setOwner(user);
		//TODO我觉得这样的设计有一点问题，不是太好，虽然会显得比较面向对象的编程
		//TODO还有一个好处就是级联操作吧！
		this.valueObject.setAuthor(user.getUserName());
		articleService.addArticle(this.valueObject);
		System.out.println("保存成功");
		return "returnHome";
	}
	/**  
	* @Name: photoUI
	* @Description:跳转到相册的页面
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-27（创建日期）
	* @Reture : photoUI /WEB-INF/page/blogphoto.jsp
	*/
	public String photoUI(){
		User user = SessionUtil.getGlobelUser(request);
		//查询得到用户的所有相册
		List<Album> albums = albumService.findAllAlbumsByUserId(user.getId());
		//查询用户所有相册的type类型
		List<SystemDDL> types = albumService.findAlbumTypeByUserId(user.getId());
		request.setAttribute("albums", albums);
		request.setAttribute("types", types);
		return "photoUI";
	}
	/**  
	* @Name: showOneArticle
	* @Description:显示一篇文章
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-01（创建日期）
	* @Reture : photoUI /WEB-INF/page/blogphoto.jsp
	*/
	public String showOneArticle(){
		String article_id = request.getParameter("article_id");
		Article article = articleService.findArticleById(article_id,true);
		request.setAttribute("article", article);
		return "article";
	}
	public String deleteArticle(){
		
		String article_id = request.getParameter("article_id");
		//调用业务层的方法删除一篇文章
		articleService.delArticleById(article_id);
		System.out.println("删除文章成功！");
		return null;
	}
	/**  
	* @Name: editArticleUI
	* @Description:跳转到编辑文章的页面
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-03（创建日期）
	* @Reture : photoUI /WEB-INF/page/editArticle.jsp
	*/
	public String editArticleUI(){
		
		//获取所有的keyword对象，并保存到request中
		List<KeyWord> keyWords = keyWordService.getAllKeyWord();
		request.setAttribute("keyWords", keyWords);		
		//获取所有的Type对象，并保存到request中
		List<Type> types = typeService.findAllType();
		request.setAttribute("types", types);
		//获取得到文章
		String article_id = request.getParameter("article_id");
		Article article = articleService.findArticleById(article_id,false);
		//TODO 由于对获取值站的值没有思路，所以暂时才比较愚笨的方法
		//将content添加到request中
		request.setAttribute("content", article.getContent());
		
		ActionContext.getContext().getValueStack().pop();//清空栈顶的值
		ActionContext.getContext().getValueStack().push(article);//将对象放置到栈顶中
		return "edit";
		
	}
	public String editArticle(){
		
		//设置更新的时间
		this.valueObject.setUpdateDate(new Date());
		articleService.updateArticle(this.valueObject);
		System.out.println("更新成功");
		return "returnHome";
	}
}
