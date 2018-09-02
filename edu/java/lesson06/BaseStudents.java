package lesson06;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor Kunakbaev Artem
 */
public class BaseStudents implements IBaseStudents<Student> {
    private String log;
    private Connection conn;
    private PreparedStatement pst;
    private Statement st;

    public BaseStudents() {
        log = "Программа готова к работе\n\n\n\n";
    }

    String getLog() {
        return log;
    }

    @Override
    public void createNewBase() {
        try {
            conn = MyConnection.getConnection();
            st = conn.createStatement();
            st.execute(SQLCommands.CREATE_TABLE);
            log = "База создана";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteTable() {
        try {
            conn = MyConnection.getConnection();
            st = conn.createStatement();
            st.execute(SQLCommands.DELETE_TABLE);
            log = "База данных удалена";
        } catch (Exception e) {
            log = "База данных уже удалена";
        } finally {
            try {
                conn.close();
//                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void insert(Student student) {
        try {
            conn = MyConnection.getConnection();
            pst = conn.prepareStatement(SQLCommands.ADD_NEW_STUDENT_2);
            pst.setString(1, student.getName());
            pst.setInt(2, student.getRating());
            pst.executeUpdate();
            student.setId(getIdToName(student.getName()));
            log = "Студент добавлен:\n" + student.toString();
        } catch (Exception e) {
            log = "База данных отсутствует";
        } finally {
            try {
                conn.close();
//                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Student student) {
        try {
            conn = MyConnection.getConnection();
            pst = conn.prepareStatement(SQLCommands.UPDATE_STUDENT_3);
            pst.setString(1, student.getName());
            pst.setInt(2, student.getRating());
            pst.setInt(3, student.getId());
            pst.executeUpdate();
            log = "Информация о студенте обновлена:\n" + student;
        } catch (Exception e) {
            log = "Такой студент отсутствует";
        } finally {
            try {
//                pst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        try {
            conn = MyConnection.getConnection();
            pst = conn.prepareStatement(SQLCommands.DELETE_STUDENT_1);
            pst.setInt(1, id);
            pst.executeUpdate();
            log = "Студент с id " + id + " удален";
        } catch (SQLException | NullPointerException e) {
            log = "Такой студент отсутствует";
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Student get(int id) {
        Student student = null;
        try {
            conn = MyConnection.getConnection();
            pst = conn.prepareStatement(SQLCommands.SHOW_STUDENT_1);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                student = new Student(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3));
            }
            assert student != null;
            log = student.toString();
        } catch (SQLException | NullPointerException e) {
            log = "Такой студент осутствует";
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return student;
    }

    @Override
    public List<Student> getAll() {
        List<Student> list = new ArrayList<>();
        conn = MyConnection.getConnection();
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(SQLCommands.SHOW_ALL);
            while (rs.next()) {
                list.add(new Student(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)));
            }
            log = list.toString();
        } catch (SQLException e) {
            log = "Данные отсутствуют";
        }
        return list;
    }

    void insertBatch() {
        try {
            conn = MyConnection.getConnection();
            conn.setAutoCommit(false);
            pst = conn.prepareStatement(SQLCommands.INSERT_BATCH);

            for (int i = 0; i < 1000; i++) {
                pst.setString(1, "Студент-" + i);
                pst.setInt(2, i);
                pst.addBatch();
            }
            pst.executeBatch();
            log = "Добавлено 1000 студентов";
        } catch (SQLException | NullPointerException e) {
            log = "База данных отсутствует";
        } finally {
            try {
                conn.commit();
//                pst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getIdToName(String name){
        int id = 0;
        try {
            conn = MyConnection.getConnection();
            pst = conn.prepareStatement(SQLCommands.SELECT_ID_BY_NAME);
            pst.setString(1,name);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }
}

class SQLCommands {
    static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS students(" +
            "id     INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL," +
            "name   TEXT    NOT NULL," +
            "rating INTEGER" +
            ")";
    static final String DELETE_TABLE = "DROP TABLE students";
    static final String DELETE_STUDENT_1 = "DELETE FROM students WHERE id=?";
    static final String ADD_NEW_STUDENT_2 = "INSERT INTO students (name, rating) VALUES(?,?)";
    static final String UPDATE_STUDENT_3 = "UPDATE students SET name = ?, rating = ? WHERE id = ?";
    static final String SHOW_STUDENT_1 = "SELECT id, name, rating FROM students WHERE id=?";
    static final String SHOW_ALL = "SELECT * FROM students";
    static final String INSERT_BATCH = "INSERT INTO students (name, rating) VALUES(?,?)";
    static final String SELECT_ID_BY_NAME = "SELECT id FROM students WHERE name=?";

}

class MyConnection {
    static Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:dbStudents");
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось зарегистрировать драйвер");
        }
        return conn;
    }
}

