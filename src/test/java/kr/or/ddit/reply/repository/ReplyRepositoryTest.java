package kr.or.ddit.reply.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.reply.dao.ReplyDaoI;
import kr.or.ddit.reply.model.ReplyVo;

public class ReplyRepositoryTest extends ModelTestConfig {
	private static final Logger logger = LoggerFactory.getLogger(ReplyRepositoryTest.class);
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Resource(name="ReplyRepository")
	private ReplyDaoI replyDao;

	@Test
	public void insertReplyTest() {
		/***Given***/
		ReplyVo replyVo = new ReplyVo();
		replyVo.setReply_content("댓글");
		replyVo.setPost_seq(97);
		replyVo.setUserid("brown");
		
		logger.debug("{}",replyVo);
		/***When***/
		int insertCnt = replyDao.insertReply(replyVo);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void showReplyTest() {
		/***Given***/
		int post_seq = 97; 
				
		/***When***/
		List<ReplyVo> replyList = replyDao.showReply(post_seq);
		
		/***Then***/
		assertEquals(1, replyList.size());
	}
	
	@Test
	public void deleteReplyTest() {
		/***Given***/
		int post_seq = 97; 
		
		/***When***/
		int deleteCnt = replyDao.deleteReply(post_seq);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}

}
