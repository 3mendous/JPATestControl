package ru.sbt.qa.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.sbt.qa.model.TestStands;

public interface TestStandsRepository extends CrudRepository<TestStands, TestStands> {
}