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

@WebServlet("/memberUpdate")
@MultipartConfig(maxFileSize = 1024 * 1024 * 100, maxRequestSize = 1024 * 1024 * 26)
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static MemberServiceI memberService;
	private static final Logger logger = LoggerFactory.getLogger(MemberUpdateServlet.class);

	@Override
	public void init() throws ServletException {
		memberService = new MemberService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userid = request.getParameter("userid");

		MemberVo memberVo = memberService.getMember(userid);

		request.setAttribute("memberVo", memberVo);
		request.getRequestDispatcher("member/memberUpdate.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// jsp에서 파라미터로 받는다.
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		String usernm = request.getParameter("usernm");
		String alias = request.getParameter("alias");
		String pass = request.getParameter("pass");
		String addr1 = request.getParameter("addr1");

		String addr2 = request.getParameter("addr2");
		String zipcode = request.getParameter("zipcode");
		String realfilename = request.getParameter("realfilename"); // 변경안했을 때 파일이름
		String filename = request.getParameter("filename"); // 변경안했을 때 경로이름

		// 프로필 변경에 사진을 넣었을 때
		Part profile = request.getPart("realFilename"); // 파일 선택했을 때의 type=file의 name속성을 들고옴
		String realFilename = FileUploadUtil.getFileName(profile.getHeader("Content-Disposition")); // 헤더속성을 추출해서
																									// 임의로 만든 메서드로
																									// 파일이름을 가져옴
		String extension = "." + FileUploadUtil.getExtension(realFilename);
		String fileName = UUID.randomUUID().toString() + extension; // 랜덤한 이름 부여
		String filePath = "";

		logger.debug("realfilename : {}", realfilename);
		logger.debug("filename : {}", filename);
		logger.debug("profile : {}", profile.getHeader("Content-Disposition"));
		logger.debug("extension : {}", extension);
		if (profile.getSize() > 0) { // 파일을 선택하여 name속성이 존재 할 경우
			filePath = "D:\\profile\\" + fileName;
			profile.write(filePath); // 경로 + 임의로 만든 파일이름으로 저장
		} else {
			realFilename = "";
		}

		if (filePath == "") { // 선택안했을 때
			logger.debug("프로필 선택 하여 파라미터들 : {},{},{},{},{},{},{},{},{},{}", userid, pass, usernm, alias, addr1, addr2,
					zipcode, filePath, realFilename);
			MemberVo memberVo = new MemberVo(userid, pass, usernm, alias, addr1, addr2, zipcode, filename,
					realfilename);
			int updateCnt = memberService.updateMember(memberVo);

			if (updateCnt == 1) { // 제대로 수정 됐을 경우
				response.sendRedirect(request.getContextPath() + "/member?userid=" + userid);
			} else { // 수정 실패 시
				doGet(request, response);
			}
		} else {
			logger.debug("프로필 선택 없이 파라미터들 : {},{},{},{},{},{},{},{},{},{}", userid, pass, usernm, alias, addr1, addr2,
					zipcode, filename, realfilename);
			// 프로필 선택 안했을 때 변경
			MemberVo memberVo = new MemberVo(userid, pass, usernm, alias, addr1, addr2, zipcode, filePath,
					realFilename);
			int updateCnt = memberService.updateMember(memberVo);

			if (updateCnt == 1) { // 제대로 수정 됐을 경우
				response.sendRedirect(request.getContextPath() + "/member?userid=" + userid);
			} else { // 수정 실패 시
				doGet(request, response);
			}
		}
	}
}
