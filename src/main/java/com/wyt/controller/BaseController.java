package com.wyt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
/**
 * @author weiyt
 */
@Controller
public class BaseController {

    @Value("${imgPath}")
    protected String imgPath;

    @Value("${downImgPath}")
    protected String downImgPath;


    /**
     * 分页参数
     *
     * @param params
     * @param pageIndex
     * @param pageSize
     */
    public static void initPageParam(Map<String, Object> params, int pageIndex, int pageSize) {
        params.put("start", String.valueOf(pageSize * (pageIndex - 1)));
        params.put("length", String.valueOf(pageSize));
    }

    /**
     * pdf下载
     *
     * @param request
     * @param response
     * @param url
     * @throws Exception
     */
    public void downloadPdf(HttpServletRequest request, HttpServletResponse response, String url) throws Exception {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            File f = new File(url);
            String fileName = f.getName();
            String downLoadPath = f.getAbsolutePath();
            if (!f.exists()) {
                System.out.println("文件不存在" + downLoadPath);
                return;
            }

            String agent = request.getHeader("user-agent");
            response.setContentType("application/x-download");
            if (agent.contains("Firefox")) {
                response.setHeader("Content-disposition",
                        "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            } else {
                response.setHeader("Content-Disposition",
                        "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
            }

            // 读取文件信息,返回前台
            response.setContentType("text/html;charset=utf-8");
            request.setCharacterEncoding("UTF-8");

            long fileLength = new File(downLoadPath).length();
            response.setContentType("application/x-msdownload;");

            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (bos != null) {
                bos.close();
            }
        }

    }


}
