package com.tr.DAO;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.tr.Model.CV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CVDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void add(CV cv){
        this.mongoTemplate.insert(cv);
    }

    public void update(CV cv){
        this.mongoTemplate.save(cv);
    }

    public List<CV> findCVList(Map<String, Object> filter){
        BasicDBList basicDBList=new BasicDBList();
        basicDBList.add(new BasicDBObject("company_id",filter.get("company_id")));
        basicDBList.add(new BasicDBObject("position_id",filter.get("position_id")));

        DBObject obj =new BasicDBObject();
        obj.put("$and", basicDBList);

        Query query=new BasicQuery(obj);
        query.limit((Integer) filter.get("count"));
        query.skip((Integer) filter.get("page")-1);
        query.with(new Sort(Sort.Direction.DESC, "checked"));
        query.with(new Sort(Sort.Direction.DESC, "submit_time"));

        return this.mongoTemplate.find(query, CV.class);
    }

    public List<CV> findCV(String id, String company_id, String position_id){
        BasicDBList basicDBList=new BasicDBList();
        basicDBList.add(new BasicDBObject("id",id));
        basicDBList.add(new BasicDBObject("company_id",company_id));
        basicDBList.add(new BasicDBObject("position_id",position_id));

        DBObject obj =new BasicDBObject();
        obj.put("$and", basicDBList);

        Query query=new BasicQuery(obj);

        return this.mongoTemplate.find(query, CV.class);
    }

    public long findNum(Map filter){
        BasicDBList basicDBList=new BasicDBList();
        basicDBList.add(new BasicDBObject("company_id",filter.get("company_id")));
        basicDBList.add(new BasicDBObject("position_id",filter.get("position_id")));

        DBObject obj =new BasicDBObject();
        obj.put("$and", basicDBList);

        Query query=new BasicQuery(obj);

        return this.mongoTemplate.count(query, CV.class);
    }
}
