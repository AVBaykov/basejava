package ru.javawebinar.basejava.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlHelper<T> {
    T helperExecute(PreparedStatement ps) throws SQLException;
}
