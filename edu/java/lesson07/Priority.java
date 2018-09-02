package lesson07;

/**
 * @autor Kunakbaev Artem
 */
public class Priority implements Comparable<Priority> {
    private Integer priority;
    private String name;

    public Integer getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }

    public Priority(Integer priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    @Override
    public int compareTo(Priority o) {
        if(this.priority > o.priority) return 1;
        else if(this.priority < o.priority) return -1;
        return name.compareTo(o.name);
    }
}
