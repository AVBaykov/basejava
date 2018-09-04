package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name =? WHERE uuid =?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }
            deleteContacts(resume, conn);
            deleteSections(resume, conn);
            insertContacts(resume, conn);
            insertSections(resume, conn);
            return null;
        });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();
            }
            insertContacts(resume, conn);
            insertSections(resume, conn);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("" +
                        "SELECT * FROM resume r \n" +
                        "LEFT JOIN contact c " +
                        "    ON r.uuid = c.resume_uuid " +
                        "LEFT JOIN section ss" +
                        "    ON r.uuid = ss.resume_uuid" +
                        " WHERE r.uuid =? ",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume resume = new Resume(uuid, rs.getString("full_name"));
                    do {
                        addContact(resume, rs);
                        addSection(resume, rs);
                    } while (rs.next());

                    return resume;
                });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.<Void>execute("DELETE FROM resume r WHERE r.uuid =?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionExecute(conn -> {
            Map<String, Resume> resumes = new LinkedHashMap<>();
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume r ORDER BY r.full_name, r.uuid")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    String fullName = rs.getString("full_name");
                    resumes.put(uuid, new Resume(uuid, fullName));
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM contact")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addContact(resumes.get(rs.getString("resume_uuid")), rs);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addSection(resumes.get(rs.getString("resume_uuid")), rs);
                }
            }
            return new ArrayList<>(resumes.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private void deleteContacts(Resume resume, Connection conn) {
        sqlHelper.execute("DELETE  FROM contact WHERE resume_uuid=?", ps -> {
            ps.setString(1, resume.getUuid());
            ps.execute();
            return null;
        });
    }

    private void insertContacts(Resume resume, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void addContact(Resume resume, ResultSet rs) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            resume.addContact(ContactType.valueOf(rs.getString("type")), value);
        }
    }

    private void deleteSections(Resume resume, Connection conn) {
        sqlHelper.execute("DELETE  FROM section WHERE resume_uuid=?", ps -> {
            ps.setString(1, resume.getUuid());
            ps.execute();
            return null;
        });
    }

    private void insertSections(Resume resume, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, section_type, content) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, Section> e : resume.getSections().entrySet()) {
                ps.setString(1, resume.getUuid());
                switch (e.getKey()) {
                    case PERSONAL:
                    case OBJECTIVE:
                        ps.setString(2, e.getKey().name());
                        ps.setString(3, ((TextSection) e.getValue()).getContent());
                        ps.addBatch();
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ps.setString(2, e.getKey().name());
                        String content = String.join("\n", ((ParagraphSection) e.getValue()).getParagraphList());
                        ps.setString(3, content);
                        ps.addBatch();
                        break;
                }
            }
            ps.executeBatch();
        }
    }

    private void addSection(Resume resume, ResultSet rs) throws SQLException {
        String type = rs.getString("section_type");
        if (type != null) {
            SectionType sType = SectionType.valueOf(type);
            switch (sType) {
                case PERSONAL:
                case OBJECTIVE:
                    resume.addSection(sType, new TextSection(rs.getString("content")));
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    resume.addSection(sType, new ParagraphSection(rs.getString("content").split("\n")));
                    break;
            }
        }
    }
}
