@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        User createdUser = userService.createUser(createUserRequest);
        return ResponseEntity.ok(new UserResponse(createdUser.getId(), createdUser.getFirstName(), createdUser.getLastName(), createdUser.getEmail()));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String userId) {
        User user = userService.getUser(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail()));
    }
}
