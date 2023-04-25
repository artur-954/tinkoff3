@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTest extends IntegrationEnvironment {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Order(1)
    @Transactional
    @Rollback
    public void testAddUser() {
        User user = new User(null, "John Doe", "john.doe@example.com");
        userRepository.add(user);

        Long id = jdbcTemplate.queryForObject("SELECT last_insert_id()", Long.class);
        User saved = jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class));

        assertNotNull(saved);
        assertEquals(id, saved.getId());
        assertEquals(user.getName(), saved.getName());
        assertEquals(user.getEmail(), saved.getEmail());
    }

    @Test
    @Order(2)
    @Transactional
    @Rollback
    public void testRemoveUser() {
        User user = new User(null, "John Doe", "john.doe@example.com");
        userRepository.add(user);

        Long id = jdbcTemplate.queryForObject("SELECT last_insert_id()", Long.class);
        User saved = jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
        assertNotNull(saved);

        userRepository.remove(saved);
        User deleted = jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
        assertNull(deleted);
    }

    @Test
    @Order(3)
    @Transactional
    @Rollback
    public void testFindAllUsers() {
        User user1 = new User(null, "John Doe", "john.doe@example.com");
        User user2 = new User(null, "Jane Doe", "jane.doe@example.com");
        userRepository.add(user1);
        userRepository.add(user2);

        List<User> users = userRepository.findAll();
        assertEquals(2, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }
}