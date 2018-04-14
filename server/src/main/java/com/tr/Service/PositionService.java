package com.tr.Service;

import com.tr.DAO.PositionDao;
import com.tr.Model.Position;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PositionService {

    @Autowired
    private PositionDao positionDao;

    public void add(Position position){
        this.positionDao.add(position);
    }

    public void update(Position position){
        this.positionDao.update(position);
    }

    public List<Position> find(String id, String company_id){
        return this.positionDao.findPosition(id,company_id);
    }

    public List<Position> findPositionList(Map<String, Object> filter){
        return this.positionDao.findPositionList(filter);
    }

    public long findPositionNum(Map filter){
        return this.positionDao.findNum(filter);
    }

    public String createId(String company_id){
        long currentTime=System.currentTimeMillis();
        String random= RandomStringUtils.randomAlphanumeric(3);
        String randomId = company_id + currentTime + random;
        return randomId;
    }
}
