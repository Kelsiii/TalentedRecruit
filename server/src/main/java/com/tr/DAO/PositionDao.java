package com.tr.DAO;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.tr.Model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PositionDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void add(Position position){
        this.mongoTemplate.insert(position);
    }

    public void update(Position position){
        this.mongoTemplate.save(position);
    }

    public List<Position> findPosition(String id, String company_id){
        BasicDBList basicDBList=new BasicDBList();
        basicDBList.add(new BasicDBObject("company_id", company_id));
        basicDBList.add(new BasicDBObject("_id", id));
        DBObject obj =new BasicDBObject();
        obj.put("$and", basicDBList);
        Query query=new BasicQuery(obj);

        return this.mongoTemplate.find(query, Position.class);
    }

    public List<Position> findPositionList(Map<String, Object> filter){

        BasicDBList basicDBList=new BasicDBList();
        basicDBList.add(new BasicDBObject("company_id",filter.get("company_id")));
        if(filter.get("valid") != null){
            basicDBList.add(new BasicDBObject("valid",filter.get("valid")));
        }
        if(filter.get("campus") != null){
            basicDBList.add(new BasicDBObject("campus",filter.get("campus")));
        }

        /*模糊搜索
        * pattern = Pattern.compile("^.*"+po.getTitle()+".*$", Pattern.CASE_INSENSITIVE);
        * */

        DBObject obj =new BasicDBObject();
        obj.put("$and", basicDBList);

        Query query=new BasicQuery(obj);

        query.limit((Integer) filter.get("count"));
        query.skip((Integer) filter.get("page")-1);
        query.with(new Sort(Sort.Direction.DESC, "datetime"));

        return this.mongoTemplate.find(query, Position.class);
    }

    public long findNum(Map filter){
        BasicDBList basicDBList=new BasicDBList();
        basicDBList.add(new BasicDBObject("company_id",filter.get("company_id")));
        if(filter.get("valid") != null){
            basicDBList.add(new BasicDBObject("valid",filter.get("valid")));
        }
        if(filter.get("campus") != null){
            basicDBList.add(new BasicDBObject("campus",filter.get("campus")));
        }

        DBObject obj =new BasicDBObject();
        obj.put("$and", basicDBList);

        Query query=new BasicQuery(obj);

        return this.mongoTemplate.count(query, Position.class);
    }
}
