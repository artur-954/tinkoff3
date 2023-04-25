public interface DataStorage {

    void addUser(User user);

    User getUser(long chatId);

    void addTrackedLink(TrackedLink link);

    void removeTrackedLink(TrackedLink link);

    List<TrackedLink> getTrackedLinks(long chatId);
}