@Service
public class JdbcTgChatService implements TgChatService {
    private final JdbcTgChatRepository chatRepository;

    public JdbcTgChatService(JdbcTgChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public void register(long tgChatId) {
        TgChat chat = new TgChat(tgChatId);
        chatRepository.save(chat);
    }

    @Override
    public void unregister(long tgChatId) {
        TgChat chat = chatRepository.findById(tgChatId);
        if (chat != null) {
            chatRepository.delete(chat);
        }
    }
}