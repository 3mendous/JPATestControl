package ru.sbt.qa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sbt.qa.model.Test;
import ru.sbt.qa.repositories.test.TestRepository;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/tests")
public class TestsController {

    @Autowired
    private TestRepository testRepository;
    /**
     * Method gets record from "tests" table
     * Calling by refrence: base URL + /tests/<<test_id>>
     *
     * @param testId
     * @return
     * @throws Exception
     */
    @CrossOrigin
    @GetMapping("/{testId}")
    @ResponseBody
    public Optional<Test> get(@PathVariable("testId") String testId) {
        return testRepository.findById(Long.valueOf(testId));
    }

    /**
     * Method gets record from "tests" table
     * Calling by refrence: base URL + /tests/<<test_id>>
     *
     * @return
     * @throws Exception
     */
    @CrossOrigin
    @GetMapping
    @ResponseBody
    public Iterable<Test> getAll() {
        return testRepository.findAll();
    }

    @CrossOrigin
    @PostMapping("/filter")
    @ResponseBody
    public Iterable<Test> filter(@RequestBody String jsonFilters) throws IOException {
        return testRepository.filter(jsonFilters);
    }

    @CrossOrigin
    @PostMapping
    @ResponseBody
    public void create(@RequestHeader(required = false) String sessionID,
                       @RequestBody() String json) throws IOException {
        testRepository.create(sessionID, json);
    }

    /**
     * Method updates record in "tests" table
     * Calling by refrence: base URL + /tests/<<test_id>>
     *
     * @param test_id Идентификатор теста
     * @param json    Обновленная информация о тесте в формате JSON
     * @return Результат выполнения операции
     */

    @CrossOrigin
    @PostMapping("/{test_id}")
    @ResponseBody
    public void update(@RequestHeader(required = false) String sessionID,
                                 @PathVariable("test_id") String test_id,
                                 @RequestBody() String json) throws IOException {
        testRepository.update(test_id, json);
    }

    /**
     * Method delete record in "tests" table
     * Calling by reference: base URL + /tests/<<test_id>>
     *
     * @return
     * @throws Exception
     */
    @CrossOrigin
    @DeleteMapping("/{test_id}")
    @ResponseBody
    public void delete(@PathVariable("test_id") String test_id) {
        testRepository.deleteById(Long.valueOf(test_id));
    }
}
