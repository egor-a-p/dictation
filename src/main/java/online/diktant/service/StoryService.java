package online.diktant.service;

import online.diktant.domain.Story;

public interface StoryService {

    void saveStory(Story story);

    void deleteStory(Integer id);

    Story findStory(Integer id);

    Iterable<Story> findAllStoriesByAuthor(String author);

    Iterable<Story> findAllStories();

    Iterable<String> getAuthors();

}
