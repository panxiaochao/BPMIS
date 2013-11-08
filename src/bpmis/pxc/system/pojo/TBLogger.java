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
 * 日志类
 * 
 * @ClassName: TBLogger
 * @author Mr_Pxc
 * @date 2013-7-12 下午02:43:09
 * @project_name：BPMIS_1
 * @version 1.0
 */
@Entity
@Table(name = "tb_logger")
public class TBLogger implements java.io.Serializable{
	private Integer id;
	private TBUser tbuser;
	private String account;
	private String loglevel;
	private String logcontent;
	private String broswertype;
	private String operatetime;
	private String operatetype;
	private String adrip;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	public TBUser getTbuser() {
		return tbuser;
	}

	public void setTbuser(TBUser tbuser) {
		this.tbuser = tbuser;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getLoglevel() {
		return loglevel;
	}

	public void setLoglevel(String loglevel) {
		this.loglevel = loglevel;
	}

	public String getLogcontent() {
		return logcontent;
	}

	public void setLogcontent(String logcontent) {
		this.logcontent = logcontent;
	}

	public String getBroswertype() {
		return broswertype;
	}

	public void setBroswertype(String broswertype) {
		this.broswertype = broswertype;
	}

	public String getOperatetime() {
		return operatetime;
	}

	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}

	public String getOperatetype() {
		return operatetype;
	}

	public void setOperatetype(String operatetype) {
		this.operatetype = operatetype;
	}

	public String getAdrip() {
		return adrip;
	}

	public void setAdrip(String adrip) {
		this.adrip = adrip;
	}

}
