package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.HashMap;
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
import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

@WebServlet("/memberList")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(MemberListServlet.class);
	private MemberServiceI memberService;
       
	@Override
	public void init() throws ServletException {
		memberService = new MemberService();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// page
		String page_str = request.getParameter("page"); // 최초 memberList.jsp진입 시 page값을 받아옴
		int page = page_str == null ? 1 : Integer.parseInt(page_str);
		request.setAttribute("page", page);
		
		// pageSize
		String pageSize_str = request.getParameter("pageSize"); // 최초 memberList.jsp진입 시 page값을 받아옴
		int pageSize = pageSize_str == null ? 5 : Integer.parseInt(pageSize_str);
		request.setAttribute("pageSize", pageSize);
		
		String boardname = ""; // 가짜 값
		// pageVo : page, pageSize
		PageVo pageVo = new PageVo(page, pageSize,boardname);
		
		// map객체에 page값과 pageSize 넣기
		pageVo.setPage(page);
		pageVo.setPageSize(pageSize);
		
		
		
		logger.debug("page : {}",page);
		
		Map<String, Object> map = memberService.selectMemberPageList(pageVo); // memberList를 service에서 받아옴
		
		logger.debug("memberPageList : {}" , map.get("memberList") );
		logger.debug("pages : {}" , map.get("pages") );
		
		request.setAttribute("memberPageList", map.get("memberList")); // request속성에 저장
		request.setAttribute("pages", map.get("pages")); // page속성에 저장
		request.getRequestDispatcher("/member/memberList.jsp").forward(request, response); // jsp로 포워딩
	}
}
