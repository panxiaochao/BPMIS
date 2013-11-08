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
 * 角色_菜单关联表
 * @author panxiaochao
 * @ClassName TBRoleFunction
 * @Description TODO
 * @date 2013-7-12
 */
@Entity
@Table(name="tb_role_function")
public class TBRoleFunction implements java.io.Serializable{
	private Integer id;
	private Role role;
	private Function function;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleid")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "functionid")
	public Function getFunction() {
		return function;
	}
	public void setFunction(Function function) {
		this.function = function;
	}
}
