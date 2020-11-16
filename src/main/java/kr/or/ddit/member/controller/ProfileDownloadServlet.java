package kr.or.ddit.member.controller;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.model.MemberVo;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

@WebServlet("/profileDownload")
@MultipartConfig
public class ProfileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberServiceI memberService;
	private static final Logger logger = LoggerFactory.getLogger(ProfileServlet.class);

	@Override
	public void init() throws ServletException {
		memberService = new MemberService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response content-type 설정
		response.setContentType("image/png");

		// 사용자 아이디 파라미터 확인하고
		String userid = request.getParameter("userid");
		MemberVo memberVo = memberService.getMember(userid);

		// response content-type 설정 (파일 다운로드를 위한 구문)
		response.setHeader("Content-Disposition", "attachment; filename=\"" + memberVo.getRealfilename() + "\"");
		response.setContentType("application/octet-stream");

		// 경로 확인 후 파일 입출력을 통해 응답생성
		// 파일 읽기
		// 응답 생성
		if(memberVo.getFilename() != null) {
			FileInputStream fis = new FileInputStream(memberVo.getFilename());
			ServletOutputStream sos = response.getOutputStream();
			
			byte[] buffer = new byte[512];
			
			while (fis.read(buffer) != -1) {
				sos.write(buffer);
			}
			
			fis.close();
			sos.flush();
			sos.close();
		}
	}
}
