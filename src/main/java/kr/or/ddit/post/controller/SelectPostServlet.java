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

import kr.or.ddit.attachment.model.AttachmentVo;
import kr.or.ddit.attachment.service.AttachmentService;
import kr.or.ddit.attachment.service.AttachmentServiceI;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.PostService;
import kr.or.ddit.post.service.PostServiceI;
import kr.or.ddit.reply.model.ReplyVo;
import kr.or.ddit.reply.service.ReplyService;
import kr.or.ddit.reply.service.ReplyServiceI;

@WebServlet("/selectPost")
public class SelectPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SelectPostServlet.class);
	private PostServiceI postService;
	private ReplyServiceI replyService;
	private AttachmentServiceI attachmentService;
	
	@Override
	public void init() throws ServletException {
		postService = new PostService();
		replyService = new ReplyService();
		attachmentService = new AttachmentService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int post_seq = Integer.parseInt(request.getParameter("post_seq"));
		String userid = request.getParameter("userid");
		
		PostVo postVo = postService.selectPost(post_seq);
		
		// post정보 가져옴
		
		List<AttachmentVo> attachmentList = attachmentService.selectAttachmentList(post_seq);
		// attachment정보 가져옴
		
		request.setAttribute("postVo", postVo);
		request.setAttribute("attachmentList", attachmentList);
		request.setAttribute("userid", userid);
		
		//----------------------------------post관련---------------------------
		
		List<ReplyVo> replyList = replyService.showReply(post_seq);
		
		request.setAttribute("replyList", replyList);
		
		//----------------------------------reply관련---------------------------
		
		request.getRequestDispatcher("/selectPost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
