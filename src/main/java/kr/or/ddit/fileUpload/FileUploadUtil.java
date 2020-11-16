package kr.or.ddit.fileUpload;

import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUploadUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);
	
	//FileUploadUtilTest 만들어서 테스팅
	public static String getFileName(String contentDisPosition) {
		String[] values = contentDisPosition.split("; ");
		String[] value = null;
		String result = "";
		for(int i = 0 ; i < values.length; i ++) {
			value = values[i].split("=");
			if(value[0].equals("filename")) {
				return value[1].replaceAll("\"", "");
			}
		}
		return result;
		
		
//		String[] attrs = contentDisPosition.split("; ");
//		for(String attr : attrs) {
//			String[] attrArray = attr.split("=");
//			if(attrArray[0].equals("filename")) {
//				return attrArray[1].replaceAll("\"","");
//			}
//		}
//		return  "";
	}
	
	// filename : sally.png ==> png
	public static String getExtension(String filename) {
		if(filename == null || filename.indexOf(".") == -1) {
			return "";
		}else {
			return filename.split("\\.")[1];
		}
	}
}
