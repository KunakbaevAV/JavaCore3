package lesson06;

public class Student {
    private int id;
    private String name;
    private int rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Student(int id, String name, int rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    public Student(String name, int rating) {
        this.id = 0;
        this.name = name;
        this.rating = rating;
    }

    public Student(String name){
        this.id = 0;
        this.name = name;
        this.rating = 0;
    }

    @Override
    public String toString() {
        return "Студент{" +
                "id=" + id +
                ", Имя='" + name + '\'' +
                ", Балл=" + rating +
                '}' +"\n";
    }
}
