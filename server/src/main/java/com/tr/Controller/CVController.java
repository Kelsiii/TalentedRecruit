package com.tr.Controller;

import com.google.gson.Gson;
import com.tr.Model.CV;
import com.tr.Service.CVService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/api/cv")
public class CVController {
    @Autowired
    private CVService cvService;

    @RequestMapping(value="/submit",method= {RequestMethod.POST})
    public Map<String,Object> addMethod(@RequestBody JSONObject jsonObject) {
        String company_id = jsonObject.getString("company_id");
        String position_id = jsonObject.getString("position_id");
        String id = this.cvService.createId(position_id);

        String name = jsonObject.getString("name");
        String gender = jsonObject.getString("gender");
        String education = jsonObject.getString("education");
        String tel = jsonObject.getString("tel");
        String email = jsonObject.getString("email");
        String experience = jsonObject.getString("experience");
        int checked = 0;
        String answer_id = "";//jsonObject.getString("answer_id");

        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        String submit_time = ft.format(date);

        CV cv = new CV(id,company_id,position_id,name,gender,tel,email,education,experience,
                checked,submit_time,answer_id);

        Map<String, Object> response = new LinkedHashMap<>();
        try {
            this.cvService.add(cv);
            response.put("result", 1);
        } catch (Exception e){
            response.put("result", 0);
            response.put("msg", e.getMessage());
        }

        return response;
    }

    @RequestMapping(value="/check",method= {RequestMethod.POST})
    public Map<String,Object> checkMethod(@RequestBody JSONObject jsonObject) {
        String company_id = jsonObject.getString("company_id");
        String position_id = jsonObject.getString("position_id");
        String id = jsonObject.getString("id");

        CV cv = this.cvService.findCV(id,company_id,position_id).get(0);
        cv.setChecked(1);

        Map<String, Object> response = new LinkedHashMap<>();
        try {
            this.cvService.update(cv);
            response.put("result", 1);
        } catch (Exception e){
            response.put("result", 0);
            response.put("msg", e.getMessage());
        }

        return response;
    }

    @RequestMapping(value="/get",method= {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object> getByIdMethod(@RequestBody JSONObject jsonObject) {
        String id = jsonObject.getString("id");
        String company_id = jsonObject.getString("company_id");
        String position_id = jsonObject.getString("position_id");

        Map<String, Object> response = new LinkedHashMap<>();
        try {
            List<CV> list = this.cvService.findCV(id,company_id,position_id);
            String[] list_json = new String[list.size()];
            Gson gson = new Gson();
            for(int i=0;i<list.size();i++){
                list_json[i] = gson.toJson(list.get(i));
            }

            response.put("result", 1);
            response.put("CV",list_json);

        } catch (Exception e){
            response.put("result", 0);
            response.put("msg", e.getMessage());
        }

        return response;
    }

    @RequestMapping(value="/list",method= {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object> getListMethod(@RequestBody JSONObject jsonObject) {
        String company_id = jsonObject.getString("company_id");
        String position_id = jsonObject.getString("position_id");
        int page = jsonObject.getInt("page");
        int count = jsonObject.getInt("count");

        Map<String, Object> response = new LinkedHashMap<>();
        Map<String, Object> filter = new LinkedHashMap<>();
        filter.put("company_id",company_id);
        filter.put("position_id",position_id);
        filter.put("page",page);
        filter.put("count",count);

        try {
            List<CV> list = this.cvService.findCVList(filter);
            long total = this.cvService.findCVNum(filter);
            int maxPage = (int) Math.ceil((double) total/count);


            String[] list_json = new String[list.size()];
            Gson gson = new Gson();
            for(int i=0;i<list.size();i++){
                list_json[i] = gson.toJson(list.get(i));
            }

            response.put("result", 1);
            response.put("page",page);
            response.put("total",total);
            response.put("count",count);
            response.put("maxPage", maxPage);

            response.put("CVs",list_json);

        } catch (Exception e){
            response.put("result", 0);
            response.put("msg", e.getMessage());
        }

        return response;
    }

}
