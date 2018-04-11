package ru.sbt.qa.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.sbt.qa.model.TestEntity;

public interface TestEntityRepository extends CrudRepository<TestEntity, String> {
}
