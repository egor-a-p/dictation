package info.egor_a_petrov;

import info.egor_a_petrov.api.YandexSpeechClient;
import info.egor_a_petrov.domain.entities.Story;
import info.egor_a_petrov.domain.repositories.StoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DictationApplication.class)
@WebAppConfiguration
public class DictationApplicationTests {

    private YandexSpeechClient yandexSpeechClient;
    private StoryRepository storyRepository;

    @Autowired
    public void setYandexSpeechClient(YandexSpeechClient yandexSpeechClient) {
        this.yandexSpeechClient = yandexSpeechClient;
    }

    @Autowired
    public void setStoryRepository(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Test
    public void testAudioRequest() {
        byte[] audio = yandexSpeechClient.getAudio("Штырлиц ещё никогда не был так близок к провалу.");
        assertNotNull(audio);

    }

    @Test
    public void testSaveLoadStory() {
        long storyCount = storyRepository.count();

        //setup story
        Story story = new Story();
        story.setName("TestName");
        story.setAuthor("TestAuthor");
        story.setContent("TestContent");
        story.setImage("TestImage".getBytes());
        story.setAudio("TestAudio".getBytes());

        //save story, verify has ID value after save
        assertNull(story.getId()); //null before save
        storyRepository.save(story);
        assertNotNull(story.getId());

        //fetch from DB
        Story fetchedStory = storyRepository.findOne(story.getId());

        //should not be null
        assertNotNull(fetchedStory);

        //should equal
        assertEquals(story.getId(), story.getId());
        assertEquals(story.getName(), story.getName());
        assertEquals(story.getAuthor(), story.getAuthor());
        assertEquals(story.getContent(), story.getContent());
        assertArrayEquals(story.getImage(), story.getImage());
        assertArrayEquals(story.getAudio(), story.getAudio());

        //verify count of products in DB
        assertEquals(storyCount + 1, storyRepository.count());

        //delete story
        storyRepository.delete(story.getId());
    }

}
