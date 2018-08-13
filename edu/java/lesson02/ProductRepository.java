package lesson02;

import org.sqlite.SQLiteException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ProductRepository implements IRepository<Product> {

    private Connection conn;
    private PreparedStatement pst;
    private Statement st;

    void createTable() {//создать таблицу
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:dbjava");
            String sql = "CREATE TABLE IF NOT EXISTS PRODUCT(\n" +
                    "   ID             INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL,\n" +
                    "   PRODID         INTEGER    NOT NULL,\n" +
                    "   TITLE          TEXT     NOT NULL,\n" +
                    "   COST           REAL)";
            st = conn.createStatement();
            st.execute(sql);

        } catch (SQLException e) {
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

    void dropTable() {//удалить таблицу
        try {
            conn = MyConnection.getConnection();
            pst = conn.prepareStatement("DROP TABLE PRODUCT");
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Таблица удалена");
            try {
                pst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void insert(Product entity) {//добавить новый продукт
        String sql = "INSERT INTO PRODUCT (PRODID, TITLE, COST) VALUES(?,?,?)";
        try {
            conn = MyConnection.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, entity.getProdid());
            pst.setString(2, entity.getTitle());
            pst.setFloat(3, entity.getCost());
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Product entity) {//изменить записанный продукт (не работает почему то)
        String sql = "UPDATE PRODUCT SET PRODID = ?, TITLE = ?, COST = ? WHERE TITLE = ?";
        try {
            conn = MyConnection.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(4, entity.getTitle());
            pst.setInt(1, entity.getProdid());
            pst.setString(2, entity.getTitle());
            pst.setFloat(3, entity.getCost());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {//удалить продукт
        String sql = "DELETE FROM PRODUCT WHERE id=?";
        try {
            conn = MyConnection.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Product get(int id) {//показать продукт по ID
        String sql = "SELECT ID, PRODID, TITLE, COST FROM PRODUCT WHERE ID=?";
        Product p = null;
        try {
            conn = MyConnection.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                p = new Product(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getFloat(4));
            }
        } catch (SQLiteException e) {
            System.out.println("Такая строчка отсутствует");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("БД закрыта");
            }
        }
        return p;
    }

    Product get(String title) {//показать продукт по названию
        String sql = "SELECT * FROM PRODUCT WHERE TITLE=?";
        Product p = null;
        try {
            conn = MyConnection.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, title);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                p = new Product(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getFloat(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("База данных закрыта");
            }
        }
        return p;
    }

    @Override
    public List<Product> get() {//показать все продукты
        String sql = "SELECT ID, PRODID, TITLE, COST FROM PRODUCT";
        List<Product> list = new LinkedList<>();
        try {
            conn = MyConnection.getConnection();
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getFloat(4)));
            }
        } catch (SQLiteException e) {
            System.out.println("База данных отсутствует");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("База данных закрыта");
            }
        }
        return list;
    }

    List<Product> getRange(float topCost, float downCost) {//показать продукты по цене
        String sql = "SELECT * FROM PRODUCT WHERE COST BETWEEN ? AND ?";
        List<Product> list = new LinkedList<>();
        try {
            conn = MyConnection.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setFloat(1, downCost);
            pst.setFloat(2, topCost);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                list.add(new Product(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getFloat(4)));
            }
        } catch (SQLiteException e) {
            System.out.println("База данных отсутствует");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pst.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("База данных закрыта");
            }
        }
        return list;
    }

    void insertBatch() {//пакетная вставка
        String sql = "INSERT INTO PRODUCT (PRODID, TITLE, COST) VALUES(?,?,?)";
        try {
            conn = MyConnection.getConnection();
            conn.setAutoCommit(false);
            pst = conn.prepareStatement(sql);

            for (int i = 1; i <= 10000; i++) {
                pst.setInt(1, i);
                pst.setString(2, "товар" + i);
                pst.setFloat(3, i * 10.f);
                pst.addBatch();
            }
            pst.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.commit();
                pst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
