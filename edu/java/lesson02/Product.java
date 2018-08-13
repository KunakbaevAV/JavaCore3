package lesson02;

public class Product {
    private int id;
    private int prodid;
    private String title;
    private float cost;

    Product(int id, int prodid, String title, float cost) {
        this.id = id;
        this.prodid = prodid;
        this.title = title;
        this.cost = cost;
    }

    Product(int prodid, String title, float cost) {
        this.id = 0;
        this.prodid = prodid;
        this.title = title;
        this.cost = cost;
    }


    @Override
    public String toString() {
        return "\nТовар{" +
                "id = " + id +
                ", prodid = " + prodid +
                ", title = " + title +
                ", cost = " + cost +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getProdid() {
        return prodid;
    }

    public String getTitle() {
        return title;
    }

    public float getCost() {
        return cost;
    }

    public void setProdid(int prodid) {
        this.prodid = prodid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}


