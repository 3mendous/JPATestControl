package ru.sbt.qa.model;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.sbt.qa.postgresJSON.JsonType;

import javax.persistence.*;
import java.util.Map;


@Entity
@Table(name = "chains")
@TypeDef(name = "JsonType", typeClass = JsonType.class)
public class Chains {

    @Id
    private Long id;

    private String id_order;

    private Long test_id;

    private Long starting_order;

    private String start_time;

    private String end_time;

    private Long f_exec;

    private String status;

    private String build_link;

    @Type(type = "JsonType")
    private Map<Object, Object> ula_data;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn (name = "test_id", insertable = false, updatable = false)
    private Test test;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getId_order() {
        return id_order;
    }

    public void setId_order(String id_order) {
        this.id_order = id_order;
    }

    public Long getTest_id() {
        return test_id;
    }

    public void setTest_id(Long test_id) {
        this.test_id = test_id;
    }

    public Long getStarting_order() {
        return starting_order;
    }

    public void setStarting_order(Long starting_order) {
        this.starting_order = starting_order;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public Long getF_exec() {
        return f_exec;
    }

    public void setF_exec(Long f_exec) {
        this.f_exec = f_exec;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBuild_link() {
        return build_link;
    }

    public void setBuild_link(String build_link) {
        this.build_link = build_link;
    }

    public Map<Object, Object> getUla_data() {
        return ula_data;
    }

    public void setUla_data(Map<Object, Object> ula_data) {
        this.ula_data = ula_data;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Chains(Long id, String id_order, Long test_id, Long starting_order, String start_time, String end_time, Long f_exec, String status, String build_link, Map<Object, Object> ula_data) {
        this.id = id;
        this.id_order = id_order;
        this.test_id = test_id;
        this.starting_order = starting_order;
        this.start_time = start_time;
        this.end_time = end_time;
        this.f_exec = f_exec;
        this.status = status;
        this.build_link = build_link;
        this.ula_data = ula_data;
    }

    public Chains() {
    }
}


