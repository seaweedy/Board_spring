package kr.or.ddit.mvc.view;

import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public class ProfileDownloadView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// response content-type 설정 (파일 다운로드를 위한 구문)
		response.setHeader("Content-Disposition", "attachment; filename=\"" + (String)model.get("realfilename") + "\"");
		response.setContentType("application/octet-stream");

		// 경로 확인 후 파일 입출력을 통해 응답생성
		// 파일 읽기
		// 응답 생성
		if ((String)model.get("filename") != null) {
			FileInputStream fis = new FileInputStream((String)model.get("filename"));
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
