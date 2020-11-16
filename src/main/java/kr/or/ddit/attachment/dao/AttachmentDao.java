package kr.or.ddit.attachment.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.attachment.model.AttachmentVo;
import kr.or.ddit.db.MybatisUtil;

@Repository("AttachmentRepository")
public class AttachmentDao implements AttachmentDaoI{

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int insertAttachment(AttachmentVo attachmentVo) {
		int insertCnt = sqlSession.insert("attachment.insertAttachment", attachmentVo);
		return insertCnt;
	}

	@Override
	public AttachmentVo selectAttachment(int atc_seq) {
		AttachmentVo attachmentVo = sqlSession.selectOne("attachment.selectAttachment", atc_seq);
		return attachmentVo;
	}
	
	@Override
	public List<AttachmentVo> selectAttachmentList(int post_seq) {
		List<AttachmentVo> attachmentList = sqlSession.selectList("attachment.selectAttachmentList", post_seq);
		return attachmentList;
	}

	@Override
	public int deleteAttachment(int post_seq) {
		int deleteCnt = sqlSession.delete("attachment.deleteAttachment",post_seq);
		return deleteCnt;
	}

	@Override
	public int deleteOneAttachment(int atc_seq) {
		int deleteCnt = sqlSession.delete("attachment.deleteOneAttachment", atc_seq);
		return deleteCnt;
	}

}
