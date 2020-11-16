package kr.or.ddit.attachment.model;

public class AttachmentVo {
	int atc_seq;
	String atc_fname;
	String atc_rfname;
	int post_seq;

	public AttachmentVo(){
		
	}
	public AttachmentVo(int atc_seq, String atc_fname, String atc_rfname, int post_seq) {
		this.atc_seq = atc_seq;
		this.atc_fname = atc_fname;
		this.atc_rfname = atc_rfname;
		this.post_seq = post_seq;
	}
	
	@Override
	public String toString() {
		return "AttachmentVo [atc_seq=" + atc_seq + ", atc_fname=" + atc_fname + ", atc_rfname=" + atc_rfname
				+ ", post_seq=" + post_seq + "]";
	}
	
	public int getAtc_seq() {
		return atc_seq;
	}
	public void setAtc_seq(int atc_seq) {
		this.atc_seq = atc_seq;
	}
	public String getAtc_fname() {
		return atc_fname;
	}
	public void setAtc_fname(String atc_fname) {
		this.atc_fname = atc_fname;
	}
	public String getAtc_rfname() {
		return atc_rfname;
	}
	public void setAtc_rfname(String atc_rfname) {
		this.atc_rfname = atc_rfname;
	}
	public int getPost_seq() {
		return post_seq;
	}
	public void setPost_seq(int post_seq) {
		this.post_seq = post_seq;
	}
	
	
}
