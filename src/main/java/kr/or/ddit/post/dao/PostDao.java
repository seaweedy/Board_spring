package kr.or.ddit.post.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.attachment.model.AttachmentVo;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.post.model.PostVo;

@Repository("PostDao")
public class PostDao implements PostDaoI{
	private static final Logger logger = LoggerFactory.getLogger(PostDao.class);
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;

	@Override
	public List<PostVo> selectAllPost(String boardname) {
		List<PostVo> postList = sqlSession.selectList("post.selectAllPost", boardname);
		return postList;
	}

	@Override
	public int insertPost(PostVo postVo) {
		int insertCnt = sqlSession.insert("post.insertPost", postVo);
		return insertCnt;
	}

	@Override
	public PostVo selectPost(int post_seq) {
		PostVo postVo = sqlSession.selectOne("post.selectPost", post_seq);
		return postVo;
	}

	@Override
	public int deletePost(PostVo postVo) {
		int deleteCnt = sqlSession.update("post.deletePost", postVo);
		return deleteCnt;
	}
	
	public List<PostVo> selectPostPageList(PageVo pageVo){
		return sqlSession.selectList("post.selectPostPageList",pageVo);
	}

	@Override
	public int selectPostTotalCnt(String board_name) {
		return sqlSession.selectOne("post.selectPostTotalCnt",board_name);
	}

	@Override
	public int updatePost(PostVo postVo) {
		int updatePost = sqlSession.update("post.updatePost", postVo);
		return updatePost;
	}

	@Override
	public int insertAnswerPost(PostVo postVo) {
		int insertCnt = sqlSession.insert("post.insertAnswerPost", postVo);
		return insertCnt;
	}
	
}
