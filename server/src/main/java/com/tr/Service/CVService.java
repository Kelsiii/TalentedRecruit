package com.tr.Service;

import com.tr.DAO.CVDao;
import com.tr.Model.CV;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CVService {
    @Autowired
    private CVDao cvDao;

    public void add(CV cv){
        this.cvDao.add(cv);
    }

    public void update(CV cv){
        this.cvDao.update(cv);
    }

    public List<CV> findCV(String id, String company_id, String position_id){
        return this.cvDao.findCV(id,company_id,position_id);
    }

    public List<CV> findCVList(Map<String, Object> filter){
        return this.cvDao.findCVList(filter);
    }

    public String createId(String position_id){
        long currentTime=System.currentTimeMillis();
        String random= RandomStringUtils.randomAlphanumeric(5);
        String randomId = position_id + currentTime + random;
        return randomId;
    }

    public long findCVNum(Map filter){
        return this.cvDao.findNum(filter);
    }

}
