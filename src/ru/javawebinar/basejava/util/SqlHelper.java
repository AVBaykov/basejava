package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Consumer;

public class SqlHelper {

    private final String DDL;

    public SqlHelper(String s) {
        DDL = s;
    }

    public void execute(ConnectionFactory connectionFactory, Consumer consumer) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(DDL)) {
            consumer.accept(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}

