package bpmis.pxc.system.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 反馈表
 * 
 * @author panxiaochao
 * @ClassName TBFeedBack
 * @Description TODO
 * @date 2013-8-10
 */
@Entity
@Table(name = "tb_feedback")
public class TBFeedBack implements java.io.Serializable{
	private Integer id;
	private String feedemail;
	private String feedcontent;
	private String feedtime;
	private String feedstate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFeedemail() {
		return feedemail;
	}

	public void setFeedemail(String feedemail) {
		this.feedemail = feedemail;
	}

	public String getFeedcontent() {
		return feedcontent;
	}

	public void setFeedcontent(String feedcontent) {
		this.feedcontent = feedcontent;
	}

	public String getFeedtime() {
		return feedtime;
	}

	public void setFeedtime(String feedtime) {
		this.feedtime = feedtime;
	}

	public String getFeedstate() {
		return feedstate;
	}

	public void setFeedstate(String feedstate) {
		this.feedstate = feedstate;
	}

}
