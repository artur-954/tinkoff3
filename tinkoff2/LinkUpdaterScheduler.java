import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LinkUpdaterScheduler {
    private final Logger logger = LoggerFactory.getLogger(LinkUpdaterScheduler.class);

    @Scheduled(fixedDelayString = "${app.scheduler.interval}")
    public void update() {
        logger.info("Updating links...");
        // TODO: добавить логику обновления ссылок из БД и проверки на наличие обновлений
    }
}