package kr.or.ddit.reply.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.reply.model.ReplyVo;

public class ReplyServiceTest extends ModelTestConfig{
	private static final Logger logger = LoggerFactory.getLogger(ReplyServiceTest.class);
	
	@Resource(name="ReplyService")
	private ReplyServiceI replyService;

	@Test
	public void insertReplyTest() {
		/***Given***/
		ReplyVo replyVo = new ReplyVo();
		replyVo.setPost_seq(100);
		replyVo.setReply_content("댓글");
		replyVo.setReply_status(1);
		replyVo.setUserid("brown");
		
		logger.debug("{}",replyVo);
		/***When***/

		int insertCnt = replyService.insertReply(replyVo);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void showReplyTest() {
		/***Given***/
		int post_seq = 97; 
				
		/***When***/
		List<ReplyVo> replyList = replyService.showReply(post_seq);
		
		/***Then***/
		assertEquals(2, replyList.size());
	}
	
	@Test
	public void deleteReplyTest() {
		/***Given***/
		int post_seq = 97; 
		
		/***When***/
		int deleteCnt = replyService.deleteReply(post_seq);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}

}
