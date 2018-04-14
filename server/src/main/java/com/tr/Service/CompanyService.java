package com.tr.Service;

import com.tr.DAO.CompanyDao;
import com.tr.Model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    private CompanyDao companyDao;

    public void add(Company company){
        this.companyDao.add(company);
    }

    public Company get(String id){
        return this.companyDao.findCompany(id);
    }

    public void update(Company company){
        this.companyDao.update(company);
    }
}
