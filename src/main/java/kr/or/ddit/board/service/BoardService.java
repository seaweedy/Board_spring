package kr.or.ddit.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.repository.BoardRepository;
import kr.or.ddit.board.repository.BoardRepositoryI;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.db.MybatisUtil;


@Service("BoardService")
public class BoardService implements BoardServiceI{
	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
	
	@Resource(name = "BoardRepository")
	private BoardRepositoryI boardRepository;
	
	/**
	 * 
	* @author PC-08
	* 모든 board를 불러오는 메서드
	*
	 */
	@Override
	public Map<String, Object> selectAllBoardPageList(PageVo pageVo) {
		Map<String, Object> map = new HashMap<>();
		map.put("boardList", boardRepository.selectAllBoardPageList(pageVo));
		
		int totalCnt = boardRepository.selectBoardTotalCnt();
		int pages = (int)Math.ceil((double)totalCnt/pageVo.getPageSize());
		
		map.put("pages", pages);
		return map;
	}
	
	/**
	 * 
	* @author PC-08
	* 활성화 된 board를 불러오는 메서드
	*
	 */
	@Override
	public List<BoardVo> selectActiveBoard() {
		return boardRepository.selectActiveBoard();
	}
	
	/**
	 * 
	* @author PC-08
	* 새로운 board를 생성하는 메서드
	* 
	 */
	@Override
	public int insertBoard(BoardVo boardVo) {
		return boardRepository.insertBoard(boardVo);
	}

	/**
	 * 
	* @author PC-08
	* 게시판의 활성화상태를 변경하는 메서드
	*
	 */
	@Override
	public int updateBoard(BoardVo boardVo) {
		return boardRepository.updateBoard(boardVo);
	}
}
