package online.diktant.repository;

import online.diktant.domain.Story;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends CrudRepository<Story, Integer> {

    Iterable<Story> findByAuthorOrderByNameAsc(String author);

}
