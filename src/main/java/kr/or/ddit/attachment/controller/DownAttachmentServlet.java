package kr.or.ddit.attachment.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.attachment.model.AttachmentVo;
import kr.or.ddit.attachment.service.AttachmentService;
import kr.or.ddit.attachment.service.AttachmentServiceI;
import kr.or.ddit.member.controller.ProfileServlet;
import kr.or.ddit.member.model.MemberVo;

@WebServlet("/downAttachment")
public class DownAttachmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ProfileServlet.class);
	private AttachmentServiceI attachmentService;
	
	@Override
	public void init() throws ServletException {
		attachmentService = new AttachmentService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response content-type 설정
		response.setContentType("image/png");

		// 사용자 아이디 파라미터 확인하고
		int atc_seq = Integer.parseInt(request.getParameter("atc_seq"));
		AttachmentVo attachmentVo = attachmentService.selectAttachment(atc_seq);
		
		// response content-type 설정 (파일 다운로드를 위한 구문)
		response.setHeader("Content-Disposition", "attachment; filename=\"" + attachmentVo.getAtc_rfname() + "\"");
		response.setContentType("application/octet-stream");

		// 경로 확인 후 파일 입출력을 통해 응답생성
		// 파일 읽기
		// 응답 생성
		if (attachmentVo.getAtc_fname() != null) {
			FileInputStream fis = new FileInputStream(attachmentVo.getAtc_fname());
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
