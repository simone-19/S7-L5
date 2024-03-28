package services;

import java.io.IOException;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailSender emailSender;
    @Autowired
    private UserService usersService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateUser(UserLoginDTO body) {
        User user = usersService.findByEmail(body.email());

        if (bcrypt.matches(body.password(), user.getPassword())) {
            // 3. Se le credenziali sono OK --> Genero un JWT e lo restituisco
            return jwtTools.createToken(user);
        } else {
            // 4. Se le credenziali NON sono OK --> 401
            throw new UnauthorizedException("wrong password");
        }
    }

    public User registerUser(UserPostDTO body) throws IOException {
        userRepository.findByEmail(body.email()).ifPresent(a -> {
            throw new BadRequestException("User with email " + a.getEmail() + " already exists");
        });
        User user = new User();
        user.setPassword(bcrypt.encode(body.password()));
        user.setName(body.name());
        user.setSurname(body.surname());
        user.setEmail(body.email());
        user.setRole("USER");
        User savedUser = userRepository.save(user);
        emailSender.sendRegistrationEmail(savedUser);
        return savedUser;
    }
}
