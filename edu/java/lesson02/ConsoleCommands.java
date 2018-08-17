package lesson02;

import java.util.List;
import java.util.Scanner;

/**
 * @autor Kunakbaev Artem
 */

public class ConsoleCommands implements Commands<Product> {

    private static final String SLASH = "/";
    private static final String CREATE = "/создатьтаблицу";
    private static final String ADD_BATCH = "/заполнитьтаблицу";
    private static final String COST = "/цена";
    private static final String CHANGE_COST = "/сменитьцену";
    private static final String RANGE = "/товарыпоцене";
    private static final String SHOW_ALL = "/всетовары";
    private static final String DELETE = "/очиститьтаблицу";
    private static final String END = "/выход";

    private ProductRepository repository;

    ConsoleCommands(ProductRepository repository) {
        this.repository = repository;
        Scanner sc = new Scanner(System.in);
        String command;
        System.out.println("Посмотреть все команды: " + SLASH);
        do {
            command = sc.nextLine();
            if (command.equals(SLASH)) printCommands();
            else if (command.startsWith(CREATE)) createTable();
            else if (command.startsWith(ADD_BATCH)) addBatch();
            else if (command.startsWith(COST)) getCostByTitle(command);
            else if (command.startsWith(CHANGE_COST)) changeCost(command);
            else if (command.startsWith(RANGE)) System.out.println(rangeByCost(command));
            else if (command.startsWith(SHOW_ALL)) showAll();
            else if (command.startsWith(DELETE)) deleteTable();
        } while (!END.equals(command));
    }

    @Override
    public void createTable() {
        repository.createTable();
    }

    @Override
    public void addBatch() {
        repository.insertBatch();
    }

    @Override
    public void getCostByTitle(String command) {
        String[] parts = command.split("\\s");
        String title = parts[1];
        float cost;
        try {
            cost = repository.get(title).getCost();
            System.out.println(title + " стоит " + cost + " рублей.");
        } catch (NullPointerException e) {
            System.out.println(title + " отсутствует");
        }
    }

    @Override
    public void changeCost(String command) {
        String[] parts = command.split("\\s");
        String title = parts[1];
        float newCost = (float) Integer.parseInt(parts[2]);
        Product product = repository.get(title);
        try {
            product.setCost(newCost);
            repository.update(product);
            System.out.println("Цена обновлена");
            System.out.println(repository.get(title));
        } catch (NullPointerException e) {
            System.out.println(title + " отсутствует");
        }
    }

    @Override
    public void showAll() {
        System.out.println(repository.get());
    }

    @Override
    public void deleteTable() {
        repository.dropTable();
    }

    @Override
    public List<Product> rangeByCost(String command) {
        String[] parts = command.split("\\s");
        float downCost = (float) Integer.parseInt(parts[1]);
        float topCost = (float) Integer.parseInt(parts[2]);
        return repository.getRange(topCost, downCost);
    }

    private void printCommands() {
        System.out.println(CREATE);
        System.out.println(ADD_BATCH);
        System.out.println(COST + " - Получить стоимость товара по названию");
        System.out.println(CHANGE_COST + " - Поменять стоимость товара");
        System.out.println(RANGE + " - Получить товары в пределах диапазона цен");
        System.out.println(SHOW_ALL);
        System.out.println(END);
        System.out.println(DELETE);
    }
}

