package kr.or.ddit.attachment.service;

import java.util.List;

import kr.or.ddit.attachment.model.AttachmentVo;

public interface AttachmentServiceI {
	
	int insertAttachment(AttachmentVo attachmentVo);
	
	AttachmentVo selectAttachment(int atc_seq);
	
	List<AttachmentVo>selectAttachmentList(int post_seq);
	
	int deleteAttachment(int post_seq);
	
	int deleteOneAttachment(int atc_seq);
}
