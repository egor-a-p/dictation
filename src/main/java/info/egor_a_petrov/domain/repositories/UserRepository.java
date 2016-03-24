package info.egor_a_petrov.domain.repositories;

import info.egor_a_petrov.domain.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

    User findByUsername(String username);

}
