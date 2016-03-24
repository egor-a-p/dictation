package info.egor_a_petrov.service;

import info.egor_a_petrov.domain.entities.Story;

import java.util.List;

public interface StoryService {

    void saveStory(Story story);

    void deleteStory(Integer id);

    Story findStory(Integer id);

    List<Story> findAllStoriesByAuthor(String author);

    List<Story> findAllStories();

}
