package lesson06;

import java.util.List;

public interface IBaseStudents<T> {
    void createNewBase();

    void deleteTable();

    void insert(T student);

    void update(T student);

    void delete(int id);

    T get(int id);

    List<T> getAll();
}
