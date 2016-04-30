package cn.water.cf.domain;
/**
 * @author 张淼洁
 * @descriptino 文章类型表，存放用户自定的文章类型
 */
@SuppressWarnings("serial")
public class Type implements java.io.Serializable{

	private int type_id;		//分类表的主键
	private String typeName;	//分类表的名称
	
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
	
	
}
