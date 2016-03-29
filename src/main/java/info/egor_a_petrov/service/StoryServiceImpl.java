package info.egor_a_petrov.service;


import info.egor_a_petrov.domain.entities.Story;
import info.egor_a_petrov.domain.repositories.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("storyService")
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
    public Iterable<Story> findAllStoriesByAuthor(String author) {
        return storyRepository.findByAuthorOrderByNameAsc(author);
    }

    @Override
    public Iterable<Story> findAllStories() {
        return storyRepository.findAll();
    }

    @Override
    public Iterable<String> getAuthors() {
        Set<String> authors = new HashSet<>();
        storyRepository.findAll().forEach(story -> authors.add(story.getAuthor()));
        return authors;
    }
}
