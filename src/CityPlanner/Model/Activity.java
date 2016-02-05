package CityPlanner.Model;
import java.time.Duration;
/**
 * Created by tito on 5/02/16.
 */
public class Activity {
    private String name;
    private Duration duration;

    public Activity(String name, Duration duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return this.name;
    }

    public Duration getDuration() {
        return this.duration;
    }
}
