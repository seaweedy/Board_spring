package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.fileUpload.FileUploadUtil;
import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

@WebServlet("/memberRegist")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 26 )
public class MemberRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static MemberServiceI memberService;
	private static final Logger logger = LoggerFactory.getLogger(MemberRegistServlet.class);
	
	@Override
	public void init() throws ServletException {
		memberService = new MemberService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("member/memberRegist.jsp").forward(request, response);
		// memberRegist.jsp 에서 요청을 받는다
		// memberService객체의 등록 쿼리문을 받아 등록
		// 다시 회원 목록화면으로 출력
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		String usernm = request.getParameter("usernm");
		String alias = request.getParameter("alias");
		String pass = request.getParameter("pass");
		String addr1 = request.getParameter("addr1");
		
		String addr2 = request.getParameter("addr2");
		String zipcode =  request.getParameter("zipcode");
		
		logger.debug("parameter : {},{},{},{},{},{},{}", userid, usernm, alias, pass, addr1, addr2 , zipcode);
		
		Part profile = request.getPart("realFilename"); // name속성
		logger.debug("profile : {}" , profile.getHeader("Content-Disposition"));
		
		String realFilename = FileUploadUtil.getFileName(profile.getHeader("Content-Disposition"));
		
		String extension = "."+FileUploadUtil.getExtension(realFilename);
		
		logger.debug("확장자 : {}", extension);
		String fileName = UUID.randomUUID().toString()+extension;
		String filePath = "";
		if(profile.getSize() > 0) {
			filePath = "D:\\profile\\" + fileName;
			profile.write(filePath);
		}else {
			realFilename="";
		}
		// 사용자 정보 등록
		MemberVo memberVo = new MemberVo(userid, pass, usernm, alias, addr1, addr2, zipcode, filePath, realFilename);
		int insertCnt = memberService.insertMember(memberVo); // 쿼리를 통해 등록
		
		logger.debug("insertCnt : {}" , insertCnt);
		if(insertCnt == 1) { // 정상 insert - memberList 페이지로 이동
			response.sendRedirect(request.getContextPath() + "/memberList");
		}else { // 비정상 insert
			doGet(request, response); // 화면 재요청
		}
	}
}
