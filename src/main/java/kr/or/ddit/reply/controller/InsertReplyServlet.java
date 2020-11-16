package kr.or.ddit.reply.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.reply.model.ReplyVo;
import kr.or.ddit.reply.service.ReplyService;
import kr.or.ddit.reply.service.ReplyServiceI;

@WebServlet("/insertReply")
public class InsertReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(InsertReplyServlet.class);
    private ReplyServiceI replyService;
    
    @Override
    public void init() throws ServletException {
    	replyService = new ReplyService();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String userid = request.getParameter("userid");
		int post_seq = Integer.parseInt(request.getParameter("post_seq"));
		String reply_content = request.getParameter("reply_content");
		
		ReplyVo replyVo = new ReplyVo();
		replyVo.setPost_seq(post_seq);
		replyVo.setReply_content(reply_content);
		replyVo.setUserid(userid);
	
		int insertCnt = replyService.insertReply(replyVo);
		
		if(insertCnt == 1) {
			response.sendRedirect("/selectPost?post_seq="+post_seq+"&userid="+userid);
		}else {
			response.sendRedirect("/selectPost?post_seq="+post_seq+"&userid="+userid);
		}
	}

}
