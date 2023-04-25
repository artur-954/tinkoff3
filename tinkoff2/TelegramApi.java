public interface TelegramApi {

    void sendMessage(long chatId, String text);

    void sendHtmlMessage(long chatId, String html);

    void sendMarkdownMessage(long chatId, String markdown);

    void sendPhoto(long chatId, String photoUrl);

    void sendDocument(long chatId, String documentUrl);

    void sendChatAction(long chatId, String action);

    void setWebhook(String url);

    void removeWebhook();
}