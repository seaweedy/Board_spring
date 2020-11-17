package kr.or.ddit.reply.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.reply.dao.ReplyDaoI;
import kr.or.ddit.reply.model.ReplyVo;

@Transactional
@Service("ReplyService")
public class ReplyService implements ReplyServiceI{
	
	@Resource(name="ReplyRepository")
	private ReplyDaoI replyDao;
	
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
