package bpmis.pxc.system.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * 
 * @author Panxiaochao
 * 菜单表
 */
@Entity
@Table(name="tb_function")
@PrimaryKeyJoinColumn(name = "id")
public class Function implements java.io.Serializable{
	private Integer id;
	private Integer iconsid;
	private String parentid;
	private String nodename;
	private Integer nodelevel;
	/**
	 * 1是目录；0是节点
	 */
	private String isnode;
	private String nodeurl;
	private String nodeview;
	private String remark;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIconsid() {
		return iconsid;
	}
	public void setIconsid(Integer iconsid) {
		this.iconsid = iconsid;
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
	
	
}
