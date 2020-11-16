package kr.or.ddit.post.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.attachment.model.AttachmentVo;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.post.model.PostVo;

public interface PostServiceI {
	List<PostVo> selectAllPost(String boardname);
	
	int insertPost(PostVo postVo);
	
	int insertAnswerPost(PostVo postVo);
	
	PostVo selectPost(int post_seq);
	
	int deletePost(PostVo postVo);
	
	Map<String, Object> selectPostPageList(PageVo pageVo);
	
	int updatePost(PostVo postVo);
}
