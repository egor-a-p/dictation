package info.egor_a_petrov.domain.repositories;

import info.egor_a_petrov.domain.entities.Story;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends CrudRepository<Story, Integer> {

    List<Story> findByAuthorOrderByNameAsc(String author);

}
