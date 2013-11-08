package bpmis.pxc.system.templetepojo;

public class TempIconFunction {
	private Integer id;
	private String parentid;
	private String nodename;
	private Integer nodelevel;
	private String isnode;
	private String nodeurl;
	private String nodeview;
	private String remark;
	private String iconsname;
	//--add
	private String _parentId;
	private boolean state = true;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getNodename() {
		return nodename;
	}
	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	public Integer getNodelevel() {
		return nodelevel;
	}
	public void setNodelevel(Integer nodelevel) {
		this.nodelevel = nodelevel;
	}
	public String getIsnode() {
		return isnode;
	}
	public void setIsnode(String isnode) {
		this.isnode = isnode;
	}
	public String getNodeurl() {
		return nodeurl;
	}
	public void setNodeurl(String nodeurl) {
		this.nodeurl = nodeurl;
	}
	public String getNodeview() {
		return nodeview;
	}
	public void setNodeview(String nodeview) {
		this.nodeview = nodeview;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIconsname() {
		return iconsname;
	}
	public void setIconsname(String iconsname) {
		this.iconsname = iconsname;
	}
	public String get_parentId() {
		return _parentId;
	}
	public void set_parentId(String parentId) {
		_parentId = parentId;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	
}
