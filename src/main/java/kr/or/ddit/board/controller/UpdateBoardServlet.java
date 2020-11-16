package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.common.model.PageVo;

@WebServlet("/updateBoard")
public class UpdateBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static BoardServiceI boardService;
	private static final Logger logger = LoggerFactory.getLogger(UpdateBoardServlet.class);
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// page
		String page_str = request.getParameter("page"); // 최초 insertBoardForm.jsp진입 시 page값을 받아옴
		int page = page_str == null ? 1 : Integer.parseInt(page_str);
		request.setAttribute("page", page);
		
		// pageSize
		String pageSize_str = request.getParameter("pageSize"); // 최초 insertBoardForm.jsp진입 시 page값을 받아옴
		int pageSize = pageSize_str == null ? 10 : Integer.parseInt(pageSize_str);
		request.setAttribute("pageSize", pageSize);
		
		// pageVo : page, pageSize
		PageVo pageVo = new PageVo();
		
		// map객체에 page값과 pageSize 넣기
		pageVo.setPage(page);
		pageVo.setPageSize(pageSize);
		
		logger.debug("page : {}", page);
		logger.debug("pageSize : {}", pageSize);
		
		String boardname = request.getParameter("boardname");
		int boardstatus = Integer.parseInt(request.getParameter("boardstatus"));
		
		BoardVo boardVo = new BoardVo(boardstatus, boardname);
		
		int updateCnt = boardService.updateBoard(boardVo);
		
		Map<String, Object> map = boardService.selectAllBoardPageList(pageVo);
		
		List<BoardVo> activeBoardList = boardService.selectActiveBoard();
		logger.debug("activeBoardList : {}", activeBoardList.size());
		
		request.setAttribute("boardList", map.get("boardList")); // request속성에 저장
		request.setAttribute("pages", map.get("pages")); // page속성에 저장
		
		request.getSession().setAttribute("activeBoardList", activeBoardList);
		
		if(updateCnt == 1) {
			request.getRequestDispatcher("/InsertBoardForm.jsp").forward(request, response);
			logger.debug("게시판변경성공");
		}else {
			request.getRequestDispatcher("/main.jsp").forward(request, response);
			logger.debug("게시판변경실패");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
