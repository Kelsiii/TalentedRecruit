package com.tr.Controller;

import com.tr.Model.User;
import com.tr.Service.AccountService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value="/add",method= {RequestMethod.POST})
    public Map<String,Object> addMethod(@RequestBody JSONObject jsonObject) {
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String company_id = jsonObject.getString("company_id");
        String pwd = "default";

        User user = new User(id,name,pwd,company_id);
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            this.accountService.add(user);
            response.put("result", 1);
        } catch (Exception e){
            response.put("result", 0);
            response.put("msg", e.getMessage());
        }

        return response;
    }

    @RequestMapping(value="/login",method= {RequestMethod.POST})
    public Map<String,Object> loginMethod(@RequestBody JSONObject jsonObject) {
        String id = jsonObject.getString("id");
        String pwd = jsonObject.getString("pwd");
        System.out.println(id+" "+pwd);
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            boolean success = this.accountService.login(id, pwd);
            if(success){
                User user = this.accountService.findUser(id);
                response.put("result", 1);
                response.put("company_id", user.getCompany());
            } else {
                response.put("result", 0);
                response.put("msg", "密码错误");
            }
        } catch (Exception e){
            response.put("result", 0);
            response.put("msg", e.getMessage());
        }

        return response;
    }

    @RequestMapping(value="/psw/update",method= {RequestMethod.POST})
    public Map<String,Object> pwdUpdateMethod(@RequestBody JSONObject jsonObject) {
        String id = jsonObject.getString("id");
        String pwd_old = jsonObject.getString("pwd_old");
        String pwd_new = jsonObject.getString("pwd_new");
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            boolean success = this.accountService.updatePwd(id,pwd_old,pwd_new);
            if(success){
                response.put("result", 1);
            } else {
                response.put("result", 0);
                response.put("msg", "密码错误");
            }
        } catch (Exception e){
            response.put("result", 0);
            response.put("msg", e.getMessage());
        }

        return response;
    }


}
