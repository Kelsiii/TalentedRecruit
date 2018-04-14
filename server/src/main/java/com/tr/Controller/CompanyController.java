package com.tr.Controller;

import com.google.gson.Gson;
import com.tr.Model.Company;
import com.tr.Service.CompanyService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value="/update",method= {RequestMethod.POST})
    public Map<String,Object> updateMethod(@RequestBody JSONObject jsonObject) {
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String industry = jsonObject.getString("industry");
        String address = jsonObject.getString("address");
        String city = jsonObject.getString("city");
        String scale = jsonObject.getString("scale");
        String process = jsonObject.getString("process");
        String description = jsonObject.getString("description");

        Company company = new Company(id,name,address,industry,city,scale,process,description);

        Map<String, Object> response = new LinkedHashMap<>();
        try {
            this.companyService.update(company);
            response.put("result", 1);
        } catch (Exception e){
            response.put("result", 0);
            response.put("msg", e.getMessage());
        }

        return response;
    }

    @RequestMapping(value="/add",method= {RequestMethod.POST})
    public Map<String,Object> addMethod(@RequestBody JSONObject jsonObject) {
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String industry = "";
        String address = "";
        String city = "";
        String scale = "";
        String process = "";
        String description = "";

        Company company = new Company(id,name,address,industry,city,scale,process,description);

        Map<String, Object> response = new LinkedHashMap<>();
        try {
            this.companyService.add(company);
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
        Map<String, Object> response = new LinkedHashMap<>();
        try {

            Gson gson = new Gson();
            String[] company = {gson.toJson(this.companyService.get(id))};
            response.put("result", 1);
            response.put("company",company);

        } catch (Exception e){
            response.put("result", 0);
            response.put("msg", e.getMessage());
        }

        return response;
    }
}
