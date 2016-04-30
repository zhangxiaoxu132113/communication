package cn.water.cf.domain;

@SuppressWarnings("serial")
public class SystemDDL implements java.io.Serializable{

	private int seq_id;			//主键
	private String keyword;		//数据类型
	private int ddlCode;		//数据项的code
	private String ddlName;		//数据项的value
	
	public int getSeq_id() {
		return seq_id;
	}
	public void setSeq_id(int seq_id) {
		this.seq_id = seq_id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getDdlCode() {
		return ddlCode;
	}
	public void setDdlCode(int ddlCode) {
		this.ddlCode = ddlCode;
	}
	public String getDdlName() {
		return ddlName;
	}
	public void setDdlName(String ddlName) {
		this.ddlName = ddlName;
	}
	
	
	
}
