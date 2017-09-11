package com.wyt.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 文件工厂 author hc.L spring mvc 上传方式 controller 参数 MultipartFile类型 命名必须是 file
 */
public class FileUtils {

	private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

	/*
	 * depositUrl 获得方式
	 * request.getSession().getServletContext().getRealPath("upload");
	 * "upload"根路径下一级文件夹 根据javaee 版本 6以上 getServletContext 可不用从session获得
	 * 通过request可获得file对象 MultipartHttpServletRequest multipartRequest =
	 * (MultipartHttpServletRequest) request; CommonsMultipartFile file =
	 * (CommonsMultipartFile) multipartRequest.getFile("file");
	 */

	/**
	 * 单文件上传 spring-web(mvc)字节 author hc.L
	 * 
	 * @param file
	 *            文件
	 * @param depositUrl
	 *            文件存放根路径
	 * @return String 保存文件全路径（包含文件名称）
	 */
	public static FileInfo uploadFile(MultipartFile file, String depositUrl) {

		// 上传文件名称 包含文件格式
		String fileName = file.getOriginalFilename();
		// 获得文件格式
		String fileFormat = "";
		if (!StringUtils.isEmpty(fileName)) {
			fileFormat = fileName.substring(fileName.lastIndexOf('.'),
					fileName.length());
		}

		// 使用uuid+当前时间毫秒值 生成文件保存名称
		String id = UUID.randomUUID() + "" + new Date().getTime();
		File exsit = new File(depositUrl);

		// 如果路径不存在创建新的路径
		if (!exsit.exists()) {
			exsit.mkdirs();
		}
		File f = new File(depositUrl, id + fileFormat);

		try {
			long size = file.getSize();
			if (size != 0) {
				file.transferTo(f);// 保存文件至磁盘路径 Path
				return new FileInfo(id + fileFormat, depositUrl + id
						+ fileFormat, fileName, fileFormat, size, null, null);
			}
		} catch (Exception e) {
			log.error("uploadFile:", e);
		}

		return null;

	}

	/**
	 * 多文件上传 spring-web(mvc)字节 author hc.L
	 * 
	 * @param files
	 *            文件集
	 * @param depositUrl
	 *            文件存放根路径
	 * @return List<String> 保存全路径对象集(包含文件名)
	 */
	public static List<FileInfo> uploadFiles(MultipartFile[] files,
                                             String depositUrl) {
		List<FileInfo> fileInfos = new ArrayList<FileInfo>();
		for (MultipartFile file : files) {
			FileInfo info = uploadFile(file, depositUrl);
			if (!StringUtils.isEmpty(info.getId())) {
				fileInfos.add(info);
			}
		}
		return fileInfos;
	}

	/**
	 * 文件下载 字节 author hc.L
	 * 
	 * @param response
	 * @param depositUrl
	 *            文件存放路径 (注:必须以“/”结尾)
	 * @param fileName
	 *            文件名称
	 */
	public static void downFile(HttpServletResponse response,
                                String depositUrl, String showName) {
		response.setContentType("text/html;charset=UTF-8");

		// request.setCharacterEncoding("UTF-8");
		File f = new File(depositUrl);
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(f));// 输入流
			out = new BufferedOutputStream(response.getOutputStream());// 输出流
			// 输出流 对文件信息指定
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ showName);
			response.setHeader("Content-Length", String.valueOf(f.length()));

			byte[] data = new byte[1024];
			int len = 0;
			while (-1 != (len = in.read(data, 0, data.length))) {
				out.write(data, 0, len);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 保存文件
	 * 
	 * @param path
	 *            文件保存路径
	 * @param filename
	 *            文件名
	 * @param file
	 *            需要保存的文件
	 */
	public static void saveFile(String path, String filename, File file) {
		// 创建文件夹
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			fos = new FileOutputStream(path + filename);
			fis = new FileInputStream(file);
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = fis.read(buf)) > 0) {
				fos.write(buf, 0, len);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				fos.close();
				fis.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	}

	/**
	 * 文件读取 为字符串(字符) author hc.L
	 * 
	 * @param fileUrl
	 *            文件地址
	 * @return String
	 */
	public static String fileReader(String fileUrl) {
		StringBuffer filetxt = new StringBuffer();
		FileReader reader = null;
		try {
			reader = new FileReader(fileUrl);
			int len = 0;
			do {
				char[] buffer = new char[512];
				len = reader.read(buffer);
				if (len > 0) {
					filetxt.append(new String(buffer, 0, len));
				}
			} while (len > 0);

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		return filetxt.toString();
	}

	/**
	 * 文件读取为字符串（字节） author hc.L
	 * 
	 * @param fileUrl
	 *            文件地址
	 * @return
	 */
	public static String fileReaderByte(String fileUrl) {
		StringBuffer filetxt = new StringBuffer();

		File file = new File(fileUrl);
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			int byteread = 0;
			do {
				byte[] tempbytes = new byte[1024];
				byteread = in.read(tempbytes, 0, tempbytes.length);
				if (byteread > 0)
					filetxt.append(new String(tempbytes, 0, byteread));
			} while (byteread > 0);

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return filetxt.toString();

	}

	/**
	 * 字符串写入文件 (字符) author hc.L
	 * 
	 * @param weiterUrl
	 *            写入文件存放路径（文件不存在，自动新建）
	 * @param isAdd
	 *            是否追加
	 * @param txt
	 *            文本
	 * @return boolean 写入成功
	 */
	public static boolean fileWriter(String writerUrl, String txt, Boolean isAdd) {

		FileWriter writer = null;
		try {
			writer = new FileWriter(writerUrl, isAdd);
			writer.write(txt);

		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return true;
	}

	/**
	 * 字符串写入文件(字节) author hc.L
	 * 
	 * @param writerUrl
	 *            文件地址
	 * @param txt
	 *            写入文本
	 * @param isAdd
	 *            是否追加
	 * @return boolean 写入成功
	 */
	public static boolean fileWriterByte(String writerUrl, String txt,
                                         Boolean isAdd) {

		FileOutputStream out = null;
		try {
			out = new FileOutputStream(writerUrl, isAdd);
			out.write(txt.getBytes());

		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return true;

	}

	/**
	 * 删除文件
	 * 
	 * @param path
	 * @return
	 */
	public static boolean deleteFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
			return true;
		}
		return false;
	}

	public static String toUtf8String(HttpServletRequest request,
                                      String fileName) {
		try {
			String agent = request.getHeader("User-Agent");

			boolean isFireFox = (agent != null && agent.toLowerCase().indexOf(
					"firefox") != -1);
			if (isFireFox) {

				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");

			} else {
				fileName = toUtf8String(fileName);
				if ((agent != null && agent.indexOf("MSIE") != -1)) {
					// see http://support.microsoft.com/default.aspx?kbid=816868
					if (fileName.length() > 150) {
						// 根据request的locale 得出可能的编码
						fileName = new String(fileName.getBytes("UTF-8"),
								"ISO8859-1");
					}
				}
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		return fileName;
	}

	public static String toUtf8String(String fileName) {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < fileName.length(); i++) {
			char c = fileName.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {

					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}
	//删除文件夹
	public static void clearFiles(String path){
		File file = new File(path);
		if(file.exists()){
		    deleteFiles(file);
		}
		file.delete();
	}
	
	 public static void deleteFiles(File file){
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(int i=0; i<files.length; i++){
				deleteFiles(files[i]);
			}
		}
		file.delete();
	}

}
