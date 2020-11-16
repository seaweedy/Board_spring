package kr.or.ddit.post.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.post.dao.PostDao;
import kr.or.ddit.post.dao.PostDaoI;
import kr.or.ddit.post.model.PostVo;

@Service("PostService")
public class PostService implements PostServiceI{
	
	@Resource(name="PostDao")
	private PostDaoI postDao;
	
	@Override
	public List<PostVo> selectAllPost(String boardname) {
		return postDao.selectAllPost(boardname);
	}

	@Override
	public int insertPost(PostVo postVo) {
		return postDao.insertPost(postVo);
	}

	@Override
	public PostVo selectPost(int post_seq) {
		return postDao.selectPost(post_seq);
	}

	@Override
	public int deletePost(PostVo postVo) {
		return postDao.deletePost(postVo);
	}

	@Override
	public Map<String, Object> selectPostPageList(PageVo pageVo) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("postList", postDao.selectPostPageList(pageVo));
		map.put("boardname",pageVo.getBoard_name());
		
		int totalCnt = postDao.selectPostTotalCnt(pageVo.getBoard_name()); // 총 개수를 출력
		int pages = (int)Math.ceil((double)totalCnt/pageVo.getPageSize());
		
		map.put("pages", pages);
		return map;
	}

	@Override
	public int updatePost(PostVo postVo) {
		return postDao.updatePost(postVo);
	}

	@Override
	public int insertAnswerPost(PostVo postVo) {
		return postDao.insertAnswerPost(postVo);
	}

}
