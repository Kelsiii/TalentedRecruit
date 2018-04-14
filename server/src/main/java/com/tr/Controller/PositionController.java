package com.tr.Controller;

import com.google.gson.Gson;
import com.tr.Model.Position;
import com.tr.Service.PositionService;
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
@RequestMapping(value="/api/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @RequestMapping(value="/add",method= {RequestMethod.POST})
    public Map<String,Object> addMethod(@RequestBody JSONObject jsonObject) {
        String company_id = jsonObject.getString("company_id");
        String id = this.positionService.createId(company_id);

        String name = jsonObject.getString("name");

        String description = jsonObject.getString("description");
        description = description.replaceAll("\n","</br>");

        String address = jsonObject.getString("address");
        String experience = jsonObject.getString("experience");
        String education = jsonObject.getString("education");
        String type = jsonObject.getString("type");
        int campus = jsonObject.getInt("campus");
        int valid = jsonObject.getInt("valid");
        String salary = jsonObject.getString("salary");
        String tags = jsonObject.getString("tags");

        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        String datetime = ft.format(date);


        Position position = new Position(id,company_id,name,description,address,experience,education,
                type,campus,valid,datetime,salary,tags);

        Map<String, Object> response = new LinkedHashMap<>();
        try {
            this.positionService.add(position);
            response.put("result", 1);
        } catch (Exception e){
            response.put("result", 0);
            response.put("msg", e.getMessage());
        }

        return response;
    }

    @RequestMapping(value="/update",method= {RequestMethod.POST})
    public Map<String,Object> updateMethod(@RequestBody JSONObject jsonObject) {
        String id = jsonObject.getString("id");
        String company_id = jsonObject.getString("company_id");
        String name = jsonObject.getString("name");

        String description = jsonObject.getString("description");
        description = description.replaceAll("\n","</br>");

        String address = jsonObject.getString("address");
        String experience = jsonObject.getString("experience");
        String education = jsonObject.getString("education");
        String type = jsonObject.getString("type");
        int campus = jsonObject.getInt("campus");
        int valid = jsonObject.getInt("valid");

        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        String datetime = ft.format(date);

        String salary = jsonObject.getString("salary");
        String tags = jsonObject.getString("tags");

        Position position = new Position(id,company_id,name,description,address,experience,education,
                type,campus,valid,datetime,salary,tags);

        Map<String, Object> response = new LinkedHashMap<>();

        try {
            this.positionService.update(position);
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

        Map<String, Object> response = new LinkedHashMap<>();
        try {
            List<Position> list = this.positionService.find(id,company_id);
            String[] list_json = new String[list.size()];
            Gson gson = new Gson();
            for(int i=0;i<list.size();i++){
                list_json[i] = gson.toJson(list.get(i));
            }

            response.put("result", 1);
            response.put("position",list_json);

        } catch (Exception e){
            response.put("result", 0);
            response.put("msg", e.getMessage());
        }

        return response;
    }

    @RequestMapping(value="/list",method= {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object> getListMethod(@RequestBody JSONObject jsonObject) {
        String company_id = jsonObject.getString("company_id");
        int page = jsonObject.getInt("page");
        int count = jsonObject.getInt("count");
        Object valid = jsonObject.get("valid");
        Object campus = jsonObject.get("campus");

        Map<String, Object> response = new LinkedHashMap<>();
        Map<String, Object> filter = new LinkedHashMap<>();
        filter.put("company_id",company_id);
        filter.put("valid",valid);
        filter.put("page",page);
        filter.put("count",count);
        filter.put("campus",campus);

        try {
            List<Position> list = this.positionService.findPositionList(filter);
            long total = this.positionService.findPositionNum(filter);
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

            response.put("positions",list_json);

        } catch (Exception e){
            response.put("result", 0);
            response.put("msg", e.getMessage());
        }

        return response;
    }

}
