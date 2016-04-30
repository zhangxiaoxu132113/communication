package cn.water.cf.web.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.water.cf.domain.Text;
import cn.water.cf.service.ITextService;

@Controller("textAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class TextAction extends BaseAction<Text>{

	@Resource(name=ITextService.SERVICE_NAME)
	private ITextService textService;
	
	public String add(){
		System.out.println("这个方法被执行了--------------------------------");
		textService.saveText(this.valueObject);
		return "success";
	}

}
