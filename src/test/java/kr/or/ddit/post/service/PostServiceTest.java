package kr.or.ddit.post.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.post.model.PostVo;

public class PostServiceTest extends ModelTestConfig{

	
	
	@Resource(name="PostService")
	private PostServiceI postService;
	
	@Test
	public void selectAllPostTest(){
		/***Given***/
		String boardname = "테스트게시판";

		/***When***/
		List<PostVo> postList = postService.selectAllPost(boardname);

		/***Then***/
		assertEquals(10, postList.size());
	}
	
	@Test
	public void insertPostTest() {
		/***Given***/
		PostVo postVo = new PostVo();
		postVo.setBoard_name("테스트게시판");
		postVo.setPost_content("테스트내용");
		postVo.setPost_title("테스트제목");
		postVo.setUserid("brown");
		/***When***/
		int insertCnt = postService.insertPost(postVo);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void selectPostTest() {
		/***Given***/
		
		/***When***/
		PostVo postVo = postService.selectPost(97);
		
		/***Then***/
		assertEquals("테스트제목", postVo.getPost_title());
	}
	
	@Test
	public void deletePostTest() {
		/***Given***/
		
		PostVo postVo = new PostVo();;
		postVo.setBoard_name("테스트게시판");
		postVo.setPost_content("테스트내용");
		postVo.setPost_title("테스트제목");
		postVo.setPost_seq(96);
		
		/***When***/
		int deleteCnt = postService.deletePost(postVo);
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	@Test
	public void selectPostPageListTest() {
		/***Given***/
		PageVo pageVo = new PageVo();
		pageVo.setPage(1);
		pageVo.setPageSize(5);
		pageVo.setBoard_name("테스트게시판");
		
		/***When***/
		Map<String, Object> map = postService.selectPostPageList(pageVo);
		
		/***Then***/
		assertEquals(3, map.get("pages"));
	}
	
	@Test
	public void updatePostTest() {
		/***Given***/
		PostVo postVo = new PostVo();;
		postVo.setPost_content("테스트내용");
		postVo.setPost_title("테스트제목1");
		postVo.setPost_seq(96);
		
		/***When***/
		int updateCnt = postService.updatePost(postVo);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void insertAnswerPostTest() {
		/***Given***/
		PostVo postVo = new PostVo();;
		postVo.setPost_content("테스트내용");
		postVo.setPost_title("테스트제목6");
		postVo.setBoard_name("테스트게시판");
		postVo.setUserid("brown");
		postVo.setPost_parent(96);
		
		/***When***/
		int insertCnt = postService.insertAnswerPost(postVo);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}

}
