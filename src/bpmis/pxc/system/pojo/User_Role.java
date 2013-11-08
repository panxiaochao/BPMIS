package bpmis.pxc.system.pojo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Panxiaochao
 * 用户角色关联表
 */
@Entity
@Table(name="tb_user_role")
public class User_Role implements java.io.Serializable{
	private Integer id;
	private TBUser tsuser;
	private Role role;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	public TBUser getTsuser() {
		return tsuser;
	}
	public void setTsuser(TBUser tsuser) {
		this.tsuser = tsuser;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roleid")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}	

}
