package kr.or.ddit.board.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.ModelTestConfig;
import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.common.model.PageVo;

public class BoardServiceTest extends ModelTestConfig{

	@Resource(name = "BoardService")
	private BoardServiceI boardService;

	@Test
	public void selectAllBoardPageListTest() {
		/***Given***/
		PageVo pageVo = new PageVo();
		pageVo.setPage(1);
		pageVo.setPageSize(5);
		pageVo.setBoard_name("테스트게시판");

		/***When***/
		Map<String, Object> boardList = boardService.selectAllBoardPageList(pageVo);

		/***Then***/
		assertEquals(2, boardList.size());
	}
	
	@Test
	public void selectActiveBoardTest() {
		/***Given***/
		
		/***When***/
		List<BoardVo> boardList = boardService.selectActiveBoard();
		
		/***Then***/
		assertEquals(1, boardList.size());
	}
	
	@Test
	public void insertBoardTest() {
		/***Given***/
		BoardVo boardVo = new BoardVo();
		boardVo.setBoard_name("새로운게시판");
		boardVo.setBoard_status(1);
		
		/***When***/
		int insertCnt = boardService.insertBoard(boardVo);
		
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
		int updateCnt = boardService.updateBoard(boardVo);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
}
