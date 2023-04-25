import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JpaLinkService implements LinkService {
    private final LinkRepository linkRepository;

    public JpaLinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    @Transactional
    public Link save(Link link) {
        return linkRepository.save(link);
    }

    @Override
    public Optional<Link> findById(Long id) {
        return linkRepository.findById(id);
    }
}