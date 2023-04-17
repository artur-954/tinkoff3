@Service
public class JdbcLinkUpdater implements LinkUpdater {
    private final JdbcLinkRepository linkRepository;

    public JdbcLinkUpdater(JdbcLinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public int update() {

        return 1;
    }
}