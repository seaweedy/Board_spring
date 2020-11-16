package kr.or.ddit.post.controller;

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

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.PostService;
import kr.or.ddit.post.service.PostServiceI;

@WebServlet("/selectAllPost")
public class SelectAllPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SelectAllPostServlet.class);
	private PostServiceI postService;
	
	@Override
	public void init() throws ServletException {
		postService = new PostService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// page
		String page_str = request.getParameter("page"); // 최초 memberList.jsp진입 시 page값을 받아옴
		int page = page_str == null ? 1 : Integer.parseInt(page_str);
		request.setAttribute("page", page);
		
		// pageSize
		String pageSize_str = request.getParameter("pageSize"); // 최초 memberList.jsp진입 시 page값을 받아옴
		int pageSize = pageSize_str == null ? 10 : Integer.parseInt(pageSize_str);
		request.setAttribute("pageSize", pageSize);

		// boardname
		String boardname = request.getParameter("boardname");
		
		// pageVo : page, pageSize
		PageVo pageVo = new PageVo(page, pageSize,boardname);

		// map객체에 page값과 pageSize 넣기
		pageVo.setPage(page);
		pageVo.setPageSize(pageSize);
		pageVo.setBoard_name(boardname);
		
		logger.debug("page : {}", page);

		Map<String, Object> map = postService.selectPostPageList(pageVo); // memberList를 service에서 받아옴

		logger.debug("postList : {}", map.get("postList"));
		logger.debug("pages : {}" , map.get("pages") );
		
		request.setAttribute("postList", map.get("postList")); // request속성에 저장
		request.setAttribute("pages", map.get("pages")); // page속성에 저장
		//-----------------------page------------------
		
		logger.debug("boardname : {}", boardname);
		
		request.setAttribute("boardname", boardname);
		
		request.getRequestDispatcher("/postList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
