package com.useeinfo.demo.modules.test.entity;

import com.useeinfo.framework.extend.entity.DataEntity;
import com.useeinfo.framework.sugar.tools.CommonSugar;
import net.sf.json.JSONObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "wms_system_student")
@GenericGenerator(name = "WMS_SYSTEM_STUDENT_GEN", strategy = "enhanced-table",
        parameters = {
                @Parameter(name = "table_name", value = "table_generator"),
                @Parameter(name = "value_column_name", value = "next"),
                @Parameter(name = "segment_column_name", value = "segment_name"),
                @Parameter(name = "segment_value", value = "wms_system_student_id"),
                @Parameter(name = "initial_value", value = "1000"),
                @Parameter(name = "increment_size", value = "10"),
                @Parameter(name = "optimizer", value = "pooled-lo")
        })
public class SystemStudent extends DataEntity<SystemStudent> {

    private Long studentId;
    private String studentName;

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("studentId", CommonSugar.getTypedDefault(getStudentId(), 0L));
        jsonObject.put("studentName", CommonSugar.getTypedDefault(getStudentName(), ""));


        return jsonObject;
    }

    public SystemStudent() {
    }

    public SystemStudent(long studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    @Id
    @GeneratedValue(generator = "WMS_SYSTEM_STUDENT_GEN")
    @Column(name = "student_id")
    public Long getStudentId() {
        return studentId;
    }

    @Override
    @Transient
    public Long getId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }
    @Column(name = "student_name")
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


}
