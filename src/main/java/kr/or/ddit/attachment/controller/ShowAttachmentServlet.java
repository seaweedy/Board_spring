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

@WebServlet("/showAttachment")
public class ShowAttachmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ShowAttachmentServlet.class);
	private AttachmentServiceI attachmentService;
	
	@Override
	public void init() throws ServletException {
		attachmentService = new AttachmentService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response content-type 설정
		response.setContentType("image/png");
		
		int post_seq = Integer.parseInt(request.getParameter("post_seq"));
		List<AttachmentVo> attachmentList = attachmentService.selectAttachmentList(post_seq);
		// attachment정보 가져옴
		
		// 첨부파일 표시 
		for (AttachmentVo attachmentVo : attachmentList) {
			if(attachmentVo.getAtc_fname() != null) {
				FileInputStream fis = new FileInputStream(attachmentVo.getAtc_fname());
				ServletOutputStream sos = response.getOutputStream();
				
				byte[] buffer = new byte[512];
				
				while(fis.read(buffer) != -1) {
					sos.write(buffer);
				}
				
				fis.close();
				sos.flush();
				sos.close();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
