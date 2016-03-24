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

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        try {
            byte[] audio = yandexSpeechClient.getAudio(
                    "Инфраструктура сервиса спроектирована с учетом высоких нагрузок," +
                            " чтобы обеспечивать доступность и безотказную работу системы.");
            assertNotNull(audio);
            Files.write(Paths.get("src/test/resources/test.mp3"), audio);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testAddStory() {
        //setup story
        Story story = new Story();
        try {
            story.setName("Скворцы");
            story.setAuthor("А. И. Куприн");
            story.setContent(new String(Files.readAllBytes(Paths.get("src/test/resources/7colors/content.txt")), Charset.forName("UTF-8")));
            story.setImage(Files.readAllBytes(Paths.get("src/test/resources/7colors/skv.png")));
            story.setAudio(yandexSpeechClient.getAudio(story.getContent()));
            Files.write(Paths.get("src/test/resources/test.mp3"), story.getAudio());
        } catch (IOException e) {
        }
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
