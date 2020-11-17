package kr.or.ddit.attachment.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.attachment.dao.AttachmentDaoI;
import kr.or.ddit.attachment.model.AttachmentVo;

public class AttachmentRepositoryTest extends ModelTestConfig {
	
	@Resource(name = "AttachmentRepository")
	private AttachmentDaoI attachmentDao;
	
	@Test
	public void insertAttachmentTest() {
		/***Given***/
		AttachmentVo attachmentVo = new AttachmentVo();
		attachmentVo.setAtc_fname("D:\\atch\\brown.jpg");
		attachmentVo.setAtc_rfname("brown.jpg");
		attachmentVo.setPost_seq(97);

		/***When***/
		int insertCnt = attachmentDao.insertAttachment(attachmentVo);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	@Test
	public void selectAttachmentTest() {
		/***Given***/
		int atc_seq = 65;
		
		/***When***/
		AttachmentVo attachmentVo= attachmentDao.selectAttachment(atc_seq);
		
		/***Then***/
		assertEquals(atc_seq, attachmentVo.getAtc_seq());
	}
	@Test
	public void deleteAttachmentTest() {
		/***Given***/
		int atc_seq = 64;
		
		/***When***/
		int deleteCnt = attachmentDao.deleteAttachment(atc_seq);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}
	@Test
	public void deleteOneAttachmentTest() {
		/***Given***/
		int atc_seq = 66;
		
		/***When***/
		int deleteCnt = attachmentDao.deleteOneAttachment(atc_seq);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}
	@Test
	public void selectAttachmentListTest() {
		/***Given***/
		int atc_seq = 66;
		
		/***When***/
		List<AttachmentVo> attachmentList = attachmentDao.selectAttachmentList(atc_seq);
		
		/***Then***/
		assertEquals(1, attachmentList.size());
	}
	
	

}
