package repositories;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findById(long id);

    public Optional<User> findByEmail(String email);

    public Page<User> findByEvents(Event event, Pageable pageable);


}
