package com.wyt.controller;

import com.wyt.common.HttpUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${Weiyt} on 2017/7/5.
 */
@Controller
@RequestMapping(value = "iQuery")
public class QueryController {
    public static final String APPKEY = "2cb3463ef01b547b";// 你的appkey
    public static final String URL = "http://api.jisuapi.com/iqa/query";

    @RequestMapping(value = "iQuery", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> Get(@RequestParam String queryContent) {
        Map<String, Object> resultMap = new HashMap<>();
        String result = null;
        String url = URL + "?appkey=" + APPKEY + "&question=" + queryContent;

        try {
            result = HttpUtil.sendGet(url, "utf-8");
            JSONObject json = new JSONObject(result);
            if (json.getInt("status") != 0) {
                resultMap.put("status",1);
                resultMap.put("msg", json.getString("msg"));
                return resultMap;
            }
            JSONObject resultarr = json.optJSONObject("result");
            String type = resultarr.getString("type");
            String content = resultarr.getString("content");
            String relquestion = resultarr.getString("relquestion");
            resultMap.put("type", type);
            resultMap.put("content", content);
            resultMap.put("relquestion", relquestion);
            resultMap.put("status",0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
