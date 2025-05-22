package peaksoft.java15.demo111.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.java15.demo111.entity.User;
import peaksoft.java15.demo111.exceptions.NotFoundException;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    default User getUserByEmail(String email) {
        System.out.println("Loading user from DB: " + email);
        return findByEmail(email).orElseThrow(() -> new NotFoundException("User with :" + email + " not found"));
    }

    @Query("select case when count(u) > 0 then true else false end from User u where u.email like :email")
    boolean existsByEmail(String email);

    default User getUserByName(String name) {
        return findByEmail(name).orElseThrow(() -> new NotFoundException("User with :" + name + "not found"));
    }

    default User getUserById(Long id) {
        return findById(id).orElseThrow(
                () -> new NotFoundException("User with :" + id + " not found"));
    }
}