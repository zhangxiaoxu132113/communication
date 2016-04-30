package cn.water.test.blog;

import org.junit.Test;
import org.springframework.web.util.HtmlUtils;

import cn.water.cf.utils.HTMLUtil;

public class TestSummary {
	
	@Test
	public void testContent2Summary(){
		String summary = "";
		String content = "笔者在这里所述的<img alt=\"\" src=\"\">信息均是从网上搜索聚合而来，有些是亲身做过验证的，有些不符合笔者需求，所以也没有浪费太多时间去做无用功，有需要的朋友可以自行深入探索。"+

							"<p>office办公软件的</p>强大及广泛应用，笔者在这里就不再复述了。那么操作office办公文件的需求自然也不会少，.net仗着MS的关系，一套标准API想怎么来就怎么来。可是换了java，如何来应对呢？"+
							
							"Apache POI 特点：开源免费，Apache产出物，文档全面。功能丰富，分别有针对Word、Excel和PPT的操作API，支持03和07不同格式的文件。"+
							
							"局限性：03和07不同格式的office文件，有着两套不同的API，虽然用法类似，但毕竟类名、接口名都有所变动，使用着实有些棘手。"+
							
							
							"特点：开源免费，通过JNI功能访问Windows平台下的com组件或者win32系统库来实现操控office文件的目的。API的使用类似VBA编程的风格，所以对于熟悉VBA的朋友是个福音（至少我看着没有POI的API舒服）。"+
							
							"局限性：安装Jacob时，需要一个dll文件，所以使用Jacob脱离不了Windows平台，Linux不适用。（不巧笔者的环境是Linux，所以没对Jacob研究下去）"+
							
							"特点：功能强大，支持多种格式的文件和功能，支持许多POI没能完成的功能（真的很强大！）。举例清晰，不论是03还是07格式的文件，API接口统一。更列出了与POI的对比，充分表现了自身的完美。"+
							
							"局限性：收费！收费！收费！即便每年的$你老板可以接受，但多少主观感觉上有些受制于人。"+

							"特点：一套跨平台的办公室软件套";
		summary = content.substring(0, 100);
		summary = HTMLUtil.Html2Text(summary);
		System.out.println(summary);
		//JavaScript:document.write(document.body.innerText.replace(/<.*?>/g,"")); 
	}

}
