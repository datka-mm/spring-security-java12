package peaksoft.repository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    default User getUserByEmail(String email) {
        return findByEmail(email).orElseThrow(() ->
                new EntityNotFoundException("User with " + email + " not found!")
        );
    }

    @Query("select case when count(u)>0 then true else false end from User u where u.email like :email")
    boolean existsByEmail(String email);
}
