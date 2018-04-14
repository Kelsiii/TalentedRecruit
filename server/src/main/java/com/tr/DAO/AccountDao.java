package com.tr.DAO;

import com.tr.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void add(User user) {

        this.mongoTemplate.insert(user);
    }
    public void update(User user) {

        this.mongoTemplate.save(user);
    }
    public void delete(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        this.mongoTemplate.remove(query, User.class);
    }
    public User findUser(String id) {
        return this.mongoTemplate.findById(id, User.class);
    }
    public List<User> findUserList() {
        Query query = new Query();

        return this.mongoTemplate.find(query, User.class);
    }
}