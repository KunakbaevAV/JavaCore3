package lesson05;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @autor Kunakbaev Artem
 */
class Race {
    private ArrayList<Stage> stages;
    ArrayList<Stage> getStages() { return stages; }
    Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}
