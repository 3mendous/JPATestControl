package ru.sbt.qa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sbt.qa.model.TestEntity;
import ru.sbt.qa.repositories.TestEntityRepository;
import java.io.IOException;
import java.util.Optional;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/test")
public class Controller {

    //Automatically generated repository instances
    @Autowired
    private TestEntityRepository repository;

    @RequestMapping(method = GET)
    @ResponseBody
    public Iterable<TestEntity> getAllEntities() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{name}", method = GET)
    @ResponseBody
    public Optional<TestEntity> getEntity(@PathVariable String name) {
        return repository.findById(name);
    }

    @RequestMapping(method = {POST, PATCH})
    public void createNewEntity(@RequestBody String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //JSON to entity transformation
        TestEntity entity = mapper.readValue(json, TestEntity.class);
        repository.save(entity);
    }

    @RequestMapping(value = "/{name}", method = DELETE)
    public void deleteEntity(@PathVariable String name) {
        repository.deleteById(name);
    }
}
