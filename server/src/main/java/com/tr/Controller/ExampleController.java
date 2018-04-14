package com.tr.Controller;

import com.tr.Model.User;
import com.tr.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ExampleController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value="/api/example",method= {RequestMethod.GET, RequestMethod.POST})
    public Map<String,Object> exampleMethod() {
        User account = this.accountService.findUser("sap");
        System.out.println(account.toString());
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("result", 1);
        response.put("msg", "hello!");
        response.put("description","RESTful example");
        response.put("accounts",account.toString());
        return response;
    }

    @RequestMapping(value = "/api/test",method = RequestMethod.POST)
    public String addUser(@RequestBody User user){
        return "add user:"+ user.toString();
    }

}
