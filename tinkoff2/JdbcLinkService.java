@Service
public class JdbcLinkService implements LinkService {
    private final JdbcLinkRepository linkRepository;
    private final JdbcTgChatRepository chatRepository;

    public JdbcLinkService(JdbcLinkRepository linkRepository, JdbcTgChatRepository chatRepository) {
        this.linkRepository = linkRepository;
        this.chatRepository = chatRepository;
    }

    @Override
    public Link add(long tgChatId, URI url) {
        TgChat chat = chatRepository.findById(tgChatId);
        Link link = new Link(null, url, chat);
        linkRepository.save(link);
        return link;
    }

    @Override
    public Link remove(long tgChatId, URI url) {
        TgChat chat = chatRepository.findById(tgChatId);
        Link link = linkRepository.findByUrlAndTgChat(url, chat);
        if (link != null) {
            linkRepository.delete(link);
        }
        return link;
    }

    @Override
    public Collection<Link> listAll(long tgChatId) {
        TgChat chat = chatRepository.findById(tgChatId);
        return linkRepository.findByTgChat(chat);
    }
}