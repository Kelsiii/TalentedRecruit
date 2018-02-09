package com.tr.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class ExampleController {
    @RequestMapping(value="/api/example",method= {RequestMethod.GET, RequestMethod.POST})
    public Map<String,Object> exampleMethod() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("result", 1);
        response.put("msg", "hello!");
        response.put("description","RESTful example");
        return response;
    }
}
