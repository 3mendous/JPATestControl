package ru.sbt.qa.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.sbt.qa.model.Chains;

public interface ChainsRepository extends CrudRepository<Chains, Long> {
}
