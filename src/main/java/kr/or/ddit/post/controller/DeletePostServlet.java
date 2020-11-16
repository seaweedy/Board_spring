package kr.or.ddit.post.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.attachment.service.AttachmentService;
import kr.or.ddit.attachment.service.AttachmentServiceI;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.PostService;
import kr.or.ddit.post.service.PostServiceI;

@WebServlet("/deletePost")
public class DeletePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostServiceI postService;
	private AttachmentServiceI attachmentService;
	private static final Logger logger = LoggerFactory.getLogger(DeletePostServlet.class);
	
	@Override
	public void init() throws ServletException {
		postService = new PostService();
		attachmentService = new AttachmentService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int post_seq = Integer.parseInt(request.getParameter("post_seq"));
		String board_name = request.getParameter("board_name");
		
		logger.debug("post_seq !! : {}" , post_seq);
		logger.debug("board_name !! : {}" , board_name);
		
		PostVo postVo = new PostVo();
		postVo.setPost_seq(post_seq);
		postVo.setBoard_name(board_name);
		
		int deleteCnt = postService.deletePost(postVo);
		// 게시글 삭제
		
		int deleteAttachCnt = attachmentService.deleteAttachment(post_seq);
		
		if(deleteCnt == 1) {
			response.sendRedirect("/selectAllPost?boardname="+URLEncoder.encode(board_name));
		}else {
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
