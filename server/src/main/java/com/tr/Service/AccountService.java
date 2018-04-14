package com.tr.Service;

import com.tr.DAO.AccountDao;
import com.tr.Model.User;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;

    public void add(User user) {
        this.accountDao.add(user);
    }
    public void update(User user) {
        this.accountDao.update(user);
    }
    public User findUser(String id) {
        return this.accountDao.findUser(id);
    }

    public boolean login(String id, String pwd){

        User user = this.accountDao.findUser(id);
        if(pwd.equals(user.getPwd())){
            return true;
        } else {
            return false;
        }


    }

    public boolean updatePwd(String id, String pwd_old, String pwd_new){

        User user = this.accountDao.findUser(id);
        if(pwd_old.equals(user.getPwd())){
            user.setPwd(pwd_new);
            this.accountDao.update(user);
            return true;
        } else {
            return false;
        }

    }


}