public class CommandHandler {

    private final TelegramApi telegramApi;
    private final DataStorage dataStorage;

    public CommandHandler(TelegramApi telegramApi, DataStorage dataStorage) {
        this.telegramApi = telegramApi;
        this.dataStorage = dataStorage;
    }

    public void handleStartCommand(long chatId) {
        User user = new User(chatId);
        dataStorage.addUser(user);
        telegramApi.sendMessage(chatId, "Вы зарегистрированы.");
    }

    public void handleHelpCommand(long chatId) {
        telegramApi.sendMessage(chatId, "Список команд:\n" +
                "/start -- зарегистрировать пользователя\n" +
                "/help -- вывести окно с командами\n" +
                "/track -- начать отслеживание ссылки\n" +
                "/untrack -- прекратить отслеживание ссылки\n" +
                "/list -- показать список отслеживаемых ссылок");
    }

    public void handleTrackCommand(long chatId, String link) {
        TrackedLink trackedLink = new TrackedLink(chatId, link);
        dataStorage.addTrackedLink(trackedLink);
        telegramApi.sendMessage(chatId, "Ссылка " + link + " добавлена в список отслеживаемых.");
    }

    public void handleUntrackCommand(long chatId, String link) {
        TrackedLink trackedLink = new TrackedLink(chatId, link);
        dataStorage.removeTrackedLink(trackedLink);
        telegramApi.sendMessage(chatId, "Ссылка " + link + " удалена из списка отслеживаемых.");
    }

    public void handleListCommand(long chatId) {
        List<TrackedLink> trackedLinks = dataStorage.getTrackedLinks(chatId);
        if (trackedLinks.isEmpty()) {
            telegramApi.sendMessage(chatId, "Список отслеживаемых ссылок пуст.");
        } else {
            StringBuilder messageBuilder = new StringBuilder("Список отслеживаемых ссылок:\n");
            for (TrackedLink link : trackedLinks) {
                messageBuilder.append(link.getUrl()).append("\n");
            }
            telegramApi.sendMessage(chatId, messageBuilder.toString());
        }
    }
}