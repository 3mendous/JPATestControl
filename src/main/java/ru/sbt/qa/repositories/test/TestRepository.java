package ru.sbt.qa.repositories.test;

import org.springframework.data.repository.CrudRepository;
import ru.sbt.qa.model.Test;

public interface TestRepository extends CrudRepository<Test, Long>, CustomTestRepository {
}
