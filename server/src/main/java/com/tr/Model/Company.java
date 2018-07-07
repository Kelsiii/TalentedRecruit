package com.tr.Model;

import com.google.gson.Gson;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "company")
public class Company {
    @Id
    private String id;
    private String name;
    private String address;
    private String industry;
    private String city;
    private String scale;
    private String process;
    private String description;

    public Company(String id, String name, String address, String industry, String city,
                   String scale, String process, String description){
        this.id = id;
        this.industry = industry;
        this.name = name;
        this.address = address;
        this.city = city;
        this.scale = scale;
        this.process = process;
        this.description = description;

    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }

    public String getIndustry() {
        return industry;
    }

    public String getProcess() {
        return process;
    }

    public String getScale() {
        return scale;
    }

}
