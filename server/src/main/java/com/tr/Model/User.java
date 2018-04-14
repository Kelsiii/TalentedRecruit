package com.tr.Model;

import com.google.gson.Gson;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "account") // 如果不指定collection，默认遵从命名规则
public class User {
    @Id
    private String id;
    private String name;
    private String pwd;
    private String company;

    public User(String id, String name, String pwd, String company) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.company = company;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public String getCompany() {
        return this.company;
    }

    public String getPwd() {
        return this.pwd;
    }

    public String getName() {
        return this.name;
    }

    public void setPwd(String pwd){
        this.pwd = pwd;
    }
}
