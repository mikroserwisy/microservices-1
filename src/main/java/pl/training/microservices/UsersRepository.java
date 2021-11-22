package pl.training.microservices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> getByLastName(String lastName);

    @Query("select u from UserEntity u where u.firstName = :name")
    List<UserEntity> getByFirstName(String name);

}
