import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Duration;

@Configuration
@EnableScheduling
public class ApplicationConfig {
    @Value("${app.scheduler.interval}")
    private Duration interval;

    @Bean
    public Scheduler scheduler() {
        return new Scheduler(interval);
    }
}