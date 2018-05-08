package ru.sbt.qa.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.sbt.qa.postgresJSON.JsonType;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "tests")
@TypeDef(name = "JsonType", typeClass = JsonType.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Test implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long test_id;

    @ColumnDefault(value = "anonymous")
    private String owner;

    private String a_system;

    private String type;

    @Type(type = "JsonType")
    private Map<Object, Object> job_trigger;

    @Type(type = "JsonType")
    private Map<Object, Object> groups;

    private String test_name;

    @Type(type = "JsonType")
    private Map<Object, Object> tag_names;

    @Transient
    private String[] stands;

    @JsonIgnore
    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "test")
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    private List<TestStands> testStands;

    @JsonIgnore
    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "test")
    private Set<Chains> chains;

    public Long getTest_id() {
        return test_id;
    }

    public void setTest_id(Long test_id) {
        this.test_id = test_id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getA_system() {
        return a_system;
    }

    public void setA_system(String a_system) {
        this.a_system = a_system;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<Object, Object> getJob_trigger() {
        return job_trigger;
    }

    public void setJob_trigger(Map<Object, Object> job_trigger) {
        this.job_trigger = job_trigger;
    }

    public Map<Object, Object> getGroups() {
        return groups;
    }

    public void setGroups(Map<Object, Object> groups) {
        this.groups = groups;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public Map<Object, Object> getTag_names() {
        return tag_names;
    }

    public void setTag_names(Map<Object, Object> tag_names) {
        this.tag_names = tag_names;
    }

    public List<String> getStands() {
        return this.testStands.stream().map(TestStands::getStands).collect(Collectors.toList());
    }

    public void setStands(String[] stands) {
        this.stands = stands;
    }

    public List<TestStands> getTestStands() {
        return testStands;
    }

    public void setTestStands(List<TestStands> testStands) {
        this.testStands = testStands;
    }

    public Set<Chains> getChains() {
        return chains;
    }

    public void setChains(Set<Chains> chains) {
        this.chains = chains;
    }

    @JsonIgnore
    public List<String> getStandsFromTest(){
        try {
            return Arrays.asList(this.stands);
        } catch (NullPointerException npe){
            return null;
        }
    }

    public Test(Long test_id, String owner, String a_system, String type, Map<Object, Object> job_trigger, Map<Object, Object> groups, String test_name, Map<Object, Object> tag_names) {
        this.test_id = test_id;
        this.owner = owner;
        this.a_system = a_system;
        this.type = type;
        this.job_trigger = job_trigger;
        this.groups = groups;
        this.test_name = test_name;
        this.tag_names = tag_names;
    }

    public Test() {
    }
}

