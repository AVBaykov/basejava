package ru.javawebinar.basejava.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlHelpExecutable<T> {
    T helperExecute(PreparedStatement ps) throws SQLException;
}
