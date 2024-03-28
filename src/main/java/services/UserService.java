package services;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Page<User> getUsers(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return userRepository.findAll(pageable);
    }

    public User findById(long id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User with email " + email + " not found!"));
    }


    public User findByIdAndUpdate(long id, UserDTO body) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        user.setEmail(body.email());
        user.setName(body.name());
        user.setSurname(body.surname());
        return userRepository.save(user);
    }

    public User setAdmin(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        user.setRole("ADMIN");
        return userRepository.save(user);
    }

    public User setUser(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        user.setRole("USER");
        return userRepository.save(user);
    }
}
