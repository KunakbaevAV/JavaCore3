package lesson02;

import java.util.List;

public interface Commands<T> {
    void createTable();

    void addBatch();

    void getCostByTitle(String command);

    void changeCost(String command);

    void showAll();

    void deleteTable();

    List<T> rangeByCost(String command);
}
