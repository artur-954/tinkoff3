public interface UserRepository {
    void add(User user);
    void remove(User user);
    List<User> findAll();
}