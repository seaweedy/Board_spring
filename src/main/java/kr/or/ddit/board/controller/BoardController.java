package kr.or.ddit.board.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.common.model.PageVo;

@RequestMapping("/board")
@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Resource(name="BoardService")
	private BoardServiceI boardService;
	
	@RequestMapping("/insertView")
	public String insertBoardView() {
		logger.debug("BoardController.insertBoard()진입");
		
		return "board/insertBoardForm";
	}
	
	@RequestMapping("/insert")
	public String insertBoard(BoardVo boardVo,HttpSession session) {
		logger.debug("BoardController.insert()진입");
		int insertCnt = boardService.insertBoard(boardVo);
		
		List<BoardVo> activeBoardList = boardService.selectActiveBoard();
		session.setAttribute("activeBoardList", activeBoardList);
		
		return "redirect:/board/insertView";
	}
	
	@RequestMapping("/listHTML")
	public String boardList(PageVo pageVo,Model model) {
		logger.debug("BoardController.boardList()진입");
		
		Map<String, Object> map = boardService.selectAllBoardPageList(pageVo);
		
		model.addAttribute("boardList", map.get("boardList")); // request속성에 저장
		model.addAttribute("pages", map.get("pages")); // page속성에 저장
		model.addAttribute("pageVo",	pageVo);
		
		logger.debug("boardList 확인{}",map.get("boardList") );
		logger.debug("pages 확인{}",map.get("pages") );
		
		return "board/listHTML";
	}
	
	@RequestMapping("/update")
	public String updateBoard(BoardVo boardVo, HttpSession session) {
		logger.debug("BoardController.updateBoard()진입" );
		logger.debug("bardVo : {}" , boardVo );
		
		boardService.updateBoard(boardVo);
		
		List<BoardVo> activeBoardList = boardService.selectActiveBoard();
		session.setAttribute("activeBoardList", activeBoardList);
		
		return "redirect:/board/insertView";
	}

}
