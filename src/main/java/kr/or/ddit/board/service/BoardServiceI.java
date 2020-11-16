package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.common.model.PageVo;

public interface BoardServiceI {
	Map<String, Object> selectAllBoardPageList(PageVo pageVo);
	
	List<BoardVo> selectActiveBoard();
	
	int insertBoard(BoardVo boardVo);
	
	int updateBoard(BoardVo boardVo);
}
