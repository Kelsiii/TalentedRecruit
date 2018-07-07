package com.tr.Controller;

import com.google.gson.Gson;
import com.tr.Service.MatchService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@RestController
@RequestMapping(value="/api/search")
public class SearchController {
    @Autowired
    private MatchService matchService;

    private final String USER_AGENT = "Mozilla/5.0";

    @RequestMapping(value="/",method= {RequestMethod.POST})
    public Map<String,Object> searchMethod(@RequestBody JSONObject jsonObject){
        String str = jsonObject.getString("user_input");
        String companyId = jsonObject.getString("company_id");

        Map<String, Object> response = new LinkedHashMap<>();

        try {
            String matchResult = matchService.match(str);
            if(matchResult.equals("not found")){
                response.put("type","text");
                List list = new ArrayList();
                list.add("对不起没有找到答案");
                String[] list_json = new String[list.size()];
                Gson gson = new Gson();
                for(int i=0;i<list.size();i++){
                    list_json[i] = gson.toJson(list.get(i));
                }
                response.put("content",list_json);
            } else {
                String[] results = matchResult.split("  ");
                String type = results[0].split("[.]")[0];


                for(int i=0;i<results.length;i++){
                    results[i] = results[i].split("[.]")[1];
                }

                if(type.equals("company")){
                    List list = this.matchService.getCompanyInfo(results,companyId);
                    String[] list_json = new String[list.size()];
                    Gson gson = new Gson();
                    for(int i=0;i<list.size();i++){
                        list_json[i] = gson.toJson(list.get(i));
                    }
                    response.put("type","text");
                    response.put("content",list_json);
                } else if(type.equals("position")){
                    List list = this.matchService.getPositions(results,companyId);

                    if(list.size()>0){
                        response.put("type","positions");
                    }
                    else {
                        response.put("type","text");
                        list.add("没有找到合适的岗位");

                    }
                    String[] list_json = new String[list.size()];
                    Gson gson = new Gson();
                    for(int i=0;i<list.size();i++){
                        list_json[i] = gson.toJson(list.get(i));
                    }
                    response.put("content",list_json);

                }
            }
            response.put("result", 1);
        } catch (Exception e){
            response.put("result", 0);
            response.put("msg", e.getMessage());
        }
        return response;
    }

    @RequestMapping(value="/repost",method= {RequestMethod.POST})
    public Map<String,Object> voiceMethod(@RequestBody JSONObject jsonObject){
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            String url = "http://api.xfyun.cn/v1/service/v1/iat";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection)obj.openConnection();
            con.setDoOutput(true);
            //HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            System.out.println(jsonObject.getString("X-CurTime"));
            //添加请求头
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            con.setRequestProperty("X-Param",jsonObject.getString("X-Param"));
            con.setRequestProperty("X-CheckSum",jsonObject.getString("X-CheckSum"));
            con.setRequestProperty("X-CurTime",jsonObject.getString("X-CurTime"));
            con.setRequestProperty("X-Appid",jsonObject.getString("X-Appid"));

            OutputStream out = con.getOutputStream();
            //输出流里写入POST参数

            out.write(("audio="+jsonObject.getString("audio")).getBytes());
            out.flush();
            out.close();


            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer res = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                res.append(inputLine);
            }
            in.close();

            //打印结果
            System.out.println(res.toString());

        }catch (Exception e){
            System.out.println(e.getMessage());

        }
        return response;
    }
}
