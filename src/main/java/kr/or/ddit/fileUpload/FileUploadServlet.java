package kr.or.ddit.fileUpload;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/fileUpload")
// maxFileSize : 파일 하나당 최대 허용 크기
// maxRequestSize : 요청에 담긴 모든 사이즈
// 단위 : byte
// 1MB = 1024KB = 1024 * 1024 byte
// 10MB = 1024 * 1024 * 10 byte
@MultipartConfig(maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 26 )

public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(FileUploadServlet.class);
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("get request.getContentType() : {}", request.getContentType());
		request.getRequestDispatcher("fileUpload/uploadView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userid = request.getParameter("userid");
		String img = request.getParameter("img");
		
		// jsp에서 넘겨받은 ContentType, userid, img(file)
		logger.debug("post request.getContentType() : {}", request.getContentType());
		logger.debug("userid : {}", userid);
		logger.debug("img : {}", img);
		
		Part imgPart = request.getPart("img"); // form의 name을 불러온다.
		logger.debug("getName() : {}", imgPart.getName());
		logger.debug("getSize() : {}", imgPart.getSize());
		logger.debug("getContentType() : {}", imgPart.getContentType());
		logger.debug("content-Disposition : {}",imgPart.getHeader("Content-Disposition"));
		
		String fileName = FileUploadUtil.getFileName(imgPart.getHeader("Content-Disposition"));
		
		imgPart.write("d:\\upload\\" + fileName);
		imgPart.delete();
		
//		BufferedReader br = request.getReader();
//		char[] buffer = new char[512];
//		while(br.read(buffer) != -1) {
//			logger.debug("buffer : {}" , buffer);
//			for(int i = 0; i <buffer.length ; i++) {
//				System.out.print(buffer[i]);
//			}
//		}
		
	}

}
