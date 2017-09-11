package com.wyt.controller;

import com.google.common.collect.Maps;
import com.wyt.entity.UserInfo;
import com.wyt.service.UserService;
import com.wyt.util.DateUtil;
import com.wyt.util.FileInfo;
import com.wyt.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${Weiyt} on 2017/6/8.
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController{
    @Autowired
    UserService userService;
    /**
     *
     * @return
     */
    @RequestMapping("/findUserName")
    public @ResponseBody Map<String, Object> findUserName(HttpServletRequest request) {
       Map<String, Object> result = new HashMap<>();
        String nickName = (String) request.getSession().getAttribute("user");
        result.put("nickName",nickName);
        return result;
    }
    /**
     * 查询用户详情
     * @return
     */
    @RequestMapping("/findUserInfo")
    public @ResponseBody Map<String, Object> findUserInfo(HttpServletRequest request) {
       Map<String, Object> result = new HashMap<>();
        String id = (String) request.getSession().getAttribute("id");
        UserInfo userInfo = userService.findUserInfo(id);
        result.put("userInfo",userInfo);
        return result;
    }
    /**
     *
     * @return
     */
    @RequestMapping("/changePassword")
    public @ResponseBody  Boolean changePassword(HttpServletRequest request,@RequestParam Map<String,String> param) {
        param.put("id",(String) request.getSession().getAttribute("id"));
        return userService.changePassword(param);

    }
    /**
     * 存上传图片
     *
     * @return
     */
    @RequestMapping(value = "/updArtImg", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateArImg(@RequestParam("files[]") MultipartFile file, HttpServletRequest request) {
        Map<String, Object> param = Maps.newHashMap();
        Map<String, Object> resultMap = Maps.newHashMap();
        if (file.isEmpty()) {
            throw new RuntimeException("图片为空!");
        }
        String formatDate = DateUtil.formatDate(new Date(), "yyyyMMdd");
        if (!file.isEmpty()) {
            Calendar now = Calendar.getInstance();
            String returnImgPath = downImgPath + now.get(Calendar.YEAR) + "/" + (now.get(Calendar.MONTH) + 1) + "/" + now.get(Calendar.DAY_OF_MONTH) + "/" + formatDate + "/";
            String path = imgPath + now.get(Calendar.YEAR) + "/" + (now.get(Calendar.MONTH) + 1) + "/" + now.get(Calendar.DAY_OF_MONTH) + "/" + formatDate + "/";
            FileInfo fileInfo = FileUtils.uploadFile(file, path);
            returnImgPath = returnImgPath + fileInfo.getId();
            String id = (String) request.getSession().getAttribute("id");
            param.put("id",id);
            param.put("avatar",returnImgPath);
            userService.saveAvatar(param);
            resultMap.put("filePath", returnImgPath);
            request.setAttribute("img", returnImgPath);

        }
        return resultMap;

    }

}