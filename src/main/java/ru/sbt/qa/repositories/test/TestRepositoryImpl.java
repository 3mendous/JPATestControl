package ru.sbt.qa.repositories.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sbt.qa.model.Test;
import ru.sbt.qa.model.TestStands;
import ru.sbt.qa.repositories.TestStandsRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.*;
import java.io.IOException;
import java.util.*;

public class TestRepositoryImpl implements CustomTestRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestStandsRepository testStandsRepository;

    @Override
    public void create(String owner, String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Test test = mapper.readValue(json, Test.class);
        test.setOwner(owner);
        if (test.getGroups() == null) {
            Map<Object, Object> groups = new HashMap<>();
            groups.put("groups", "public");
            test.setGroups(groups);
        }
        test = testRepository.save(test);
        Long id = test.getTest_id();
        if (!(test.getStandsFromTest() == null))
            test.getStandsFromTest().forEach(stand -> testStandsRepository.save(new TestStands(id, stand)));
    }

    @Override
    public Iterable<Test> filter(String json) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Test> query = cb.createQuery(Test.class);
        Root<Test> root = query.from(Test.class);
        query.select(root);
        List<Predicate> predicates = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(json);
        jsonObject.toMap().forEach((key, value) -> predicates.add(cb.equal(root.get(key), value)));
        query.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(query).getResultList();
    }

    @Override
    public void update(String id, String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Test testEntity = testRepository.findById(Long.valueOf(id)).get();
        JSONObject test = new JSONObject(mapper.writeValueAsString(testEntity));
        JSONObject newTest = new JSONObject(json);
        Arrays.asList(JSONObject.getNames(newTest)).forEach(key->test.put(key, newTest.get(key)));
        testEntity = mapper.readValue(test.toString(), Test.class);
        testRepository.save(testEntity);
    }
}
