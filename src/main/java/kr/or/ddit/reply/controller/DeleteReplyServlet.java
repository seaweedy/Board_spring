package kr.or.ddit.reply.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.reply.service.ReplyService;
import kr.or.ddit.reply.service.ReplyServiceI;

@WebServlet("/deleteReply")
public class DeleteReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ReplyServiceI replyService;
    
    @Override
    public void init() throws ServletException {
    	replyService = new ReplyService();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		int reply_seq = Integer.parseInt(request.getParameter("reply_seq"));
		int post_seq = Integer.parseInt(request.getParameter("post_seq"));
		
		int deleteCnt = replyService.deleteReply(reply_seq);
		
		if(deleteCnt == 1) {
			response.sendRedirect("/selectPost?post_seq="+post_seq+"&userid="+userid);
		}else {
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
