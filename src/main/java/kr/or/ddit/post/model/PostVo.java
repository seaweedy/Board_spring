package kr.or.ddit.post.model;

import java.util.Date;

public class PostVo {
	int post_seq;
	String post_title;
	String post_content;
	Date post_date;
	String board_name;
	String userid;
	int post_status;
	int post_parent;
	
	public PostVo() {
		
	}
	public PostVo(int post_seq, String post_title, String post_content, Date post_date, String board_name,
			String userid, int post_status, int post_parent) {
		this.post_seq = post_seq;
		this.post_title = post_title;
		this.post_content = post_content;
		this.post_date = post_date;
		this.board_name = board_name;
		this.userid = userid;
		this.post_status = post_status;
		this.post_parent = post_parent;
	}
	public int getPost_seq() {
		return post_seq;
	}
	public void setPost_seq(int post_seq) {
		this.post_seq = post_seq;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getPost_content() {
		return post_content;
	}
	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}
	public Date getPost_date() {
		return post_date;
	}
	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getPost_status() {
		return post_status;
	}
	public void setPost_status(int post_status) {
		this.post_status = post_status;
	}
	public int getPost_parent() {
		return post_parent;
	}
	public void setPost_parent(int post_parent) {
		this.post_parent = post_parent;
	}
	
	
}
