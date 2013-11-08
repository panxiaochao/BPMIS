package bpmis.pxc.system.pojo;

import javax.persistence.*;
/**
 * 
 * @author Panxiaochao
 * 用户表
 */
@Entity
@Table(name="tb_user")
@PrimaryKeyJoinColumn(name = "id")
public class TBUser implements java.io.Serializable{
	
	private Integer id;
	private String bpmisid;
	private String account;
	private String username;
	private String pwd;
	private String sex;
	private String birthday;
	private String email;
	private String tel;
	private String qq;
	private String regtime;
	private String remark;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getBpmisid() {
		return bpmisid;
	}
	public void setBpmisid(String bpmisid) {
		this.bpmisid = bpmisid;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
