package kr.or.ddit.reply.service;

import java.util.List;

import kr.or.ddit.reply.dao.RePlyDao;
import kr.or.ddit.reply.dao.ReplyDaoI;
import kr.or.ddit.reply.model.ReplyVo;

public class ReplyService implements ReplyServiceI{
	public ReplyDaoI replyDao;
	
	public ReplyService(){
		replyDao = new RePlyDao();
	}
	
	@Override
	public int insertReply(ReplyVo replyVo) {
		return replyDao.insertReply(replyVo);
	}

	@Override
	public List<ReplyVo> showReply(int post_seq) {
		return replyDao.showReply(post_seq);
	}

	@Override
	public int deleteReply(int reply_seq) {
		return replyDao.deleteReply(reply_seq);
	}

}
