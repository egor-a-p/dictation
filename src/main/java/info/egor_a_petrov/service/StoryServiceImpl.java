package info.egor_a_petrov.service;


import info.egor_a_petrov.domain.entities.Story;
import info.egor_a_petrov.domain.repositories.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {

    private StoryRepository storyRepository;

    @Autowired
    public void setStoryRepository(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Override
    public void saveStory(Story story) {
        storyRepository.save(story);
    }

    @Override
    public void deleteStory(Integer id) {
        storyRepository.delete(id);
    }

    @Override
    public Story findStory(Integer id) {
        return storyRepository.findOne(id);
    }

    @Override
    public List<Story> findAllStoriesByAuthor(String author) {
        return storyRepository.findByAuthorOrderByNameAsc(author);
    }

    @Override
    public List<Story> findAllStories() {
        List<Story> result = new ArrayList<>();
        storyRepository.findAll().forEach(result::add);
        return result;
    }
}
