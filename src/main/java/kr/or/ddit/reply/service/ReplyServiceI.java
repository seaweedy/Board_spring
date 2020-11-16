package kr.or.ddit.reply.service;

import java.util.List;

import kr.or.ddit.reply.model.ReplyVo;

public interface ReplyServiceI {
	int insertReply(ReplyVo replyVo);
	
	List<ReplyVo> showReply(int post_seq);
	
	int deleteReply (int reply_seq);
}
