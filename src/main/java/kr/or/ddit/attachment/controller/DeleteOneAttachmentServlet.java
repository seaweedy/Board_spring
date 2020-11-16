package kr.or.ddit.attachment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.attachment.service.AttachmentService;
import kr.or.ddit.attachment.service.AttachmentServiceI;

@WebServlet("/deleteOneAttachment")
public class DeleteOneAttachmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DeleteOneAttachmentServlet.class);
	private AttachmentServiceI attachmentService;
	
	@Override
	public void init() throws ServletException {
		attachmentService = new AttachmentService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int atc_seq = Integer.parseInt(request.getParameter("atc_seq"));
		logger.debug("atc_seq {}",atc_seq);
		int deleteCnt = attachmentService.deleteOneAttachment(atc_seq);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
