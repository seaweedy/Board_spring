package kr.or.ddit.reply.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.reply.model.ReplyVo;

@Repository("ReplyRepository")
public class RePlyDao implements ReplyDaoI{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int insertReply(ReplyVo replyVo) {
		int insertCnt = sqlSession.insert("reply.insertReply",replyVo);
		return insertCnt;
	}
	
	@Override
	public List<ReplyVo> showReply(int post_seq) {
		List<ReplyVo> replyList = sqlSession.selectList("reply.showReply",post_seq);
		return replyList;
	}
	
	@Override
	public int deleteReply(int reply_seq) {
		int deleteCnt = sqlSession.update("reply.deleteReply", reply_seq);
		return deleteCnt;
	}
	
	
}
