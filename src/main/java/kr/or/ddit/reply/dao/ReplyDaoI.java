package kr.or.ddit.reply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.reply.model.ReplyVo;

public interface ReplyDaoI {
	int insertReply(ReplyVo replyVo);
	
	List<ReplyVo> showReply(int post_seq);
	
	int deleteReply (int reply_seq);
	
}
