package kr.or.ddit.reply.model;

import java.util.Date;

public class ReplyVo {
	String reply_content;
	int reply_status    ;
	int reply_seq       ;
	int post_seq        ;
	String userid       ;
	Date reply_date		;
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public int getReply_status() {
		return reply_status;
	}
	public void setReply_status(int reply_status) {
		this.reply_status = reply_status;
	}
	public int getReply_seq() {
		return reply_seq;
	}
	public void setReply_seq(int reply_seq) {
		this.reply_seq = reply_seq;
	}
	public int getPost_seq() {
		return post_seq;
	}
	public void setPost_seq(int post_seq) {
		this.post_seq = post_seq;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getReply_date() {
		return reply_date;
	}
	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}
	@Override
	public String toString() {
		return "ReplyVo [reply_content=" + reply_content + ", reply_status=" + reply_status + ", reply_seq=" + reply_seq
				+ ", post_seq=" + post_seq + ", userid=" + userid + ", reply_date=" + reply_date + "]";
	}
	public ReplyVo(String reply_content, int reply_status, int reply_seq, int post_seq, String userid,
			Date reply_date) {
		this.reply_content = reply_content;
		this.reply_status = reply_status;
		this.reply_seq = reply_seq;
		this.post_seq = post_seq;
		this.userid = userid;
		this.reply_date = reply_date;
	}	
	
	public ReplyVo(){
		
	}
	
}
