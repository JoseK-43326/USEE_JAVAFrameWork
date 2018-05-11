package com.useeinfo.demo.modules.test.dao;


import com.useeinfo.demo.modules.test.entity.SystemStudent;
import com.useeinfo.framework.extend.dao.CrudDao;
import com.useeinfo.framework.sugar.data.QueryParam;
import com.useeinfo.framework.sugar.data.QueryUtils;
import com.useeinfo.framework.sugar.tools.CommonSugar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository("systemStudentDaoWeb")
public class SystemStudentDao implements CrudDao<SystemStudent> {

    private final static Logger logger = LoggerFactory.getLogger(SystemStudentDao.class);

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Map<String, Object> getSearchCondition(QueryParam queryParam) {
        queryParam = CommonSugar.getTypedDefault(queryParam, new QueryParam(0));
        Map<String, String> queryHash = queryParam.getSqlMap();

        Map<String, Object> conditionHash = new HashMap<>();
        if (queryHash == null || queryHash.size() == 0) {
            return conditionHash;
        }

        for (String queryKey : queryHash.keySet()) {
            String queryValue = queryHash.get(queryKey);

            switch (queryKey) {
                case "userAccount":
                    conditionHash.put("s.userAccount = ?{paramIndex} ", queryValue);
                    continue;
            }

            conditionHash.put("sutdentId < ?{paramIndex}", 0L);
            break;
        }

        return conditionHash;
    }

    @Override
    public Long totalRecord(QueryParam queryParam) {
        Map<String, Object> conditions = getSearchCondition(queryParam);
        TypedQuery<Long> typedQuery = QueryUtils.getTypedQueryByCondition("select count(s) from SystemStudent s ", conditions, entityManager, Long.class);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<SystemStudent> findList(QueryParam queryParam) {
        String sqlInfo = queryParam.joinJPQL("select s from SystemStudent s ", "order by s.studentId desc ");

        Map<String, Object> conditions = getSearchCondition(queryParam);
        TypedQuery<SystemStudent> typedQuery = QueryUtils.getTypedQueryByCondition(sqlInfo, conditions, entityManager, SystemStudent.class);

        return queryParam.findPageList(typedQuery);
    }

    @Override
    public SystemStudent findModel(Long studentId) {
        return entityManager.find(SystemStudent.class, studentId);
    }
    @Override
    public Integer add(SystemStudent systemStudent) {
        entityManager.persist(systemStudent);
        logger.info("SystemStudentDaoImpl添加systemStudent成功！");
        return 1;
    }

    @Override
    public Integer update(SystemStudent systemStudent) {
        SystemStudent existSystemStudent = entityManager.find(SystemStudent.class, systemStudent.getStudentId());
        existSystemStudent.setStudentName(systemStudent.getStudentName());
        return 1;
    }

    @Override
    public Integer delete(Long id) {
        return null;
    }


}
