package com.useeinfo.demo.modules.test.biz;


import com.useeinfo.demo.modules.test.dao.SystemStudentDao;
import com.useeinfo.demo.modules.test.entity.SystemStudent;
import com.useeinfo.framework.extend.biz.CrudBiz;
import com.useeinfo.framework.sugar.data.QueryParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("systemStudentBizWeb")
public class SystemStudentBiz extends CrudBiz<SystemStudentDao, SystemStudent> {



    public Boolean existStudentName(SystemStudent systemStudent) {
        //若角色名为空
        logger.info("角色名为：" + systemStudent.getStudentName());
        if (StringUtils.isBlank(systemStudent.getStudentName())) {
            return true;
        }

        //根据角色名查询
        QueryParam queryParam = new QueryParam(1);
        queryParam.getSqlMap().put("studentName", systemStudent.getStudentName());
        List<SystemStudent> systemStudentList = findList(queryParam);

        if (systemStudent.getStudentId() == null) {
            //新增操作，判断列表size是否为0
            return CollectionUtils.isNotEmpty(systemStudentList);
        } else {
            //修改操作，情况一：判断list长度是否为1，并且与自身相同，那么就是不存在，所以结果取反
            //修改操作，情况二：判断list长度是0
            return !((systemStudentList.size() == 1 && systemStudentList.get(0).getStudentId().equals(systemStudent.getStudentId())) || systemStudentList.size() == 0);
        }
    }




}
