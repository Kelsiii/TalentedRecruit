package com.tr.DAO;

import com.tr.Model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void add(Company company){
        this.mongoTemplate.insert(company);
    }

    public void update(Company company){
        this.mongoTemplate.save(company);
    }

    public Company findCompany(String id){
        return this.mongoTemplate.findById(id,Company.class);
    }
}
