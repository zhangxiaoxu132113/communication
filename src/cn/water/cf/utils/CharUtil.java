package cn.water.cf.utils;

public class CharUtil {
	
	private CharUtil(){
		
	}
	/**
	 * 
	 * 判断
	 */
	public static boolean isChinese(char c){
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if(ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				)
		{
			return true;
		}
		return false;
	}
	/**
	 * @description 判断字符串中是否有中文字符
	 * @param strName
	 * @return true说明有字符串中有中文字符
	 * 		   false说明字符串中没有中文字符
	 */
	public static boolean isChinese(String strName){
		char[] ch = strName.toCharArray();
		for(int i =0; i < ch.length; i++){
			char c = ch[i];
			if(isChinese(c) == true){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
//	public static void main(String[] args) {
//		String str1 = "abc";
//		String str2 = "中文";
//		CharUtil.isChinese(str1);
//		CharUtil.isChinese(str2);
//	}

}
