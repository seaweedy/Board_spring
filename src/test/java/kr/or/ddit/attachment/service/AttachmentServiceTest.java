package kr.or.ddit.attachment.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.attachment.model.AttachmentVo;

public class AttachmentServiceTest extends ModelTestConfig {
	
	@Resource(name="AttachmentService")
	private AttachmentServiceI attachmentService;

	@Test
	public void insertAttachmentTest() {
		/***Given***/
		AttachmentVo attachmentVo = new AttachmentVo();
		attachmentVo.setAtc_fname("D:\\atch\\brown.jpg");
		attachmentVo.setAtc_rfname("brown.jpg");
		attachmentVo.setPost_seq(97);

		/***When***/
		int insertCnt = attachmentService.insertAttachment(attachmentVo);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void selectAttachmentTest() {
		/***Given***/
		int atc_seq = 65;
		
		/***When***/
		AttachmentVo attachmentVo= attachmentService.selectAttachment(atc_seq);
		
		/***Then***/
		assertEquals(atc_seq, attachmentVo.getAtc_seq());
	}
	
	@Test
	public void selectAttachmentListTest() {
		/***Given***/
		int atc_seq = 66;
		
		/***When***/
		List<AttachmentVo> attachmentList = attachmentService.selectAttachmentList(atc_seq);
		
		/***Then***/
		assertEquals(1, attachmentList.size());
	}
	
	@Test
	public void deleteAttachmentTest() {
		/***Given***/
		int atc_seq = 66;
		
		/***When***/
		int deleteCnt = attachmentService.deleteAttachment(atc_seq);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	@Test
	public void deleteOneAttachmentTest() {
		/***Given***/
		int atc_seq = 66;
		
		/***When***/
		int deleteCnt = attachmentService.deleteOneAttachment(atc_seq);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}
}
