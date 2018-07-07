package com.tr.Model;

import com.google.gson.Gson;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "position")
public class Position {
    @Id
    private String id;
    private String company_id;
    private String name;
    private String description;
    private String address;
    private String experience;
    private String education;
    private String type;
    private int campus;
    private int valid;
    private String datetime;
    private String salary;
    private String tags;

    public Position(String id,String company_id, String name, String description, String address,
                    String experience, String education, String type, int campus, int valid,
                    String datetime, String salary, String tags){
        this.id = id;
        this.company_id = company_id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.experience = experience;
        this.education = education;
        this.type = type;
        this.campus = campus;
        this.valid = valid;
        this.datetime = datetime;
        this.salary = salary;
        this.tags = tags;

    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getTags() {
        return tags;
    }
}
