package kr.or.ddit.board.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.common.model.PageVo;

public class BoardRepositoryTest extends ModelTestConfig{

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Resource(name = "BoardRepository")
	private BoardRepositoryI boardRepository;
	
	@Test
	public void selectAllBoardPageListTest() {
		/***Given***/
		PageVo pageVo = new PageVo();
		pageVo.setPage(1);
		pageVo.setPageSize(5);
		pageVo.setBoard_name("테스트게시판");

		/***When***/
		List<BoardVo> boardList = boardRepository.selectAllBoardPageList(pageVo);

		/***Then***/
		assertEquals(1, boardList.size());
	}
	
	@Test
	public void selectActiveBoardTest() {
		/***Given***/
		
		/***When***/
		List<BoardVo> boardList = boardRepository.selectActiveBoard();
		
		/***Then***/
		assertEquals(2, boardList.size());
	}
	
	@Test
	public void insertBoardTest() {
		/***Given***/
		BoardVo boardVo = new BoardVo();
		boardVo.setBoard_name("추가게시판");
		boardVo.setBoard_status(1);
		
		/***When***/
		int insertCnt = boardRepository.insertBoard(boardVo);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void updateBoardTest() {
		/***Given***/
		BoardVo boardVo = new BoardVo();
		boardVo.setBoard_name("테스트게시판");
		boardVo.setBoard_status(2);
		
		/***When***/
		int updateCnt = boardRepository.updateBoard(boardVo);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void selectBoardTotalCntTest() {
		/***Given***/
		
		/***When***/
		int totalCnt = boardRepository.selectBoardTotalCnt();
		
		/***Then***/
		assertEquals(2, totalCnt);
	}

}
