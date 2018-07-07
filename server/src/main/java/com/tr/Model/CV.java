package com.tr.Model;

import com.google.gson.Gson;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cvs")
public class CV {
    @Id
    private String id;
    private String company_id;
    private String position_id;
    private String name;
    private String gender;
    private String tel;
    private String email;
    private String education;
    private String experience;
    private int checked;
    private String submit_time;
    private String answer_id;

    public CV(String id, String company_id, String position_id, String name, String gender,
              String tel, String email, String education, String experience, int checked,
              String submit_time, String answer_id){
        this.id = id;
        this.company_id = company_id;
        this.position_id = position_id;
        this.name = name;
        this.gender = gender;
        this.tel = tel;
        this.email = email;
        this.education = education;
        this.experience = experience;
        this.checked = checked;
        this.submit_time = submit_time;
        this.answer_id = answer_id;

    }

    public void setChecked(int checked){
        this.checked = checked;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
}
