package bpmis.pxc.system.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Panxiaochao
 * 图标表
 */
@Entity
@Table(name="tb_icons")
public class ICons implements java.io.Serializable{
	private Integer id;
	private String imgurl = "";
	private String imgclassname = "";
	private String imgdesc = "";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getImgclassname() {
		return imgclassname;
	}
	public void setImgclassname(String imgclassname) {
		this.imgclassname = imgclassname;
	}
	public String getImgdesc() {
		return imgdesc;
	}
	public void setImgdesc(String imgdesc) {
		this.imgdesc = imgdesc;
	}
	
	

}
