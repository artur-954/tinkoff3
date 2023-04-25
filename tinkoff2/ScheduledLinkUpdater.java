@RestController
@RequestMapping("/linkupdater")
public class ScheduledLinkUpdater {
    private final LinkUpdater linkUpdater;

    public ScheduledLinkUpdater(LinkUpdater linkUpdater) {
        this.linkUpdater = linkUpdater;
    }

    @Scheduled(cron = "0 3 * * *")
    public void updateLinks() {
        int updated = linkUpdater.update();
        System.out.println("Updated " + updated + " links");
    }
}