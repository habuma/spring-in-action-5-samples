package tacos.ingredients;
import java.util.Calendar;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

//@Component
public class WackoHealthIndicator 
       implements HealthIndicator {

  @Override
  public Health health() {
    int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    if (hour > 12) {
      return Health
          .outOfService()
          .withDetail("reason",
              "I'm out of service after lunchtime")
       .withDetail("hour", hour)
       .build();
    }
    
    if (Math.random() < 0.1) {
      return Health
          .down()
          .withDetail("reason", "I break 10% of the time")
          .build();
    }
    
    return Health
        .up()
        .withDetail("reason", "All is good!")
        .build();
  }
}
