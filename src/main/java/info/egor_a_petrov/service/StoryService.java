package info.egor_a_petrov.service;

import info.egor_a_petrov.domain.entities.Story;

public interface StoryService {

    void saveStory(Story story);

    void deleteStory(Integer id);

    Story findStory(Integer id);

    Iterable<Story> findAllStoriesByAuthor(String author);

    Iterable<Story> findAllStories();

    Iterable<String> getAuthors();

}
