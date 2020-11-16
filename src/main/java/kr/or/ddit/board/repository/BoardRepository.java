package kr.or.ddit.board.repository;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.common.model.PageVo;


@Repository("BoardRepository")
public class BoardRepository implements BoardRepositoryI {
	private static final Logger logger = LoggerFactory.getLogger(BoardRepository.class);
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	/**
	 * 
	* @author PC-08
	* 모든 board를 불러오는 메서드
	*
	 */
	@Override
	public List<BoardVo> selectAllBoardPageList(PageVo pageVo) {
		return sqlSession.selectList("board.selectAllBoardPageList",pageVo);
	}
	
	/**
	 * 
	* @author PC-08
	* 활성화 된 board를 불러오는 메서드
	*
	 */
	@Override
	public List<BoardVo> selectActiveBoard() {
		List<BoardVo> boardList = sqlSession.selectList("board.selectActiveBoard");
		return boardList;
	}

	/**
	 * 
	* @author PC-08
	* 새로운 board를 생성하는 메서드
	* 
	 */
	@Override
	public int insertBoard(BoardVo boardVo) {
		int insertCnt = sqlSession.insert("board.insertBoard",boardVo);
		return insertCnt;
	}

	/**
	 * 
	* @author PC-08
	* 게시판의 활성화상태를 변경하는 메서드
	* 
	 */
	public int updateBoard(BoardVo boardVo) {
		int updateCnt = sqlSession.update("board.updateBoard", boardVo);
		return updateCnt;
	}

	@Override
	public int selectBoardTotalCnt() {
		return sqlSession.selectOne("board.selectBoardTotalCnt");
	}

}

