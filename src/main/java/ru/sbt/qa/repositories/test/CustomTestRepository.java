package ru.sbt.qa.repositories.test;

import ru.sbt.qa.model.Test;
import java.io.IOException;

public interface CustomTestRepository {
    void create(String owner, String json) throws IOException;

    Iterable<Test> filter(String json);

    void update(String id, String json) throws IOException;
}
