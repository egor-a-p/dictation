package online.diktant.web;

import online.diktant.domain.Story;
import online.diktant.service.YandexSpeechClient;
import online.diktant.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class DictationController {
    private StoryService storyService;
    private YandexSpeechClient yandexSpeechClient;

    @Autowired
    public void setYandexSpeechClient(YandexSpeechClient yandexSpeechClient) {
        this.yandexSpeechClient = yandexSpeechClient;
    }

    @Autowired
    public void setStoryService(StoryService storyService) {
        this.storyService = storyService;
    }

    @RequestMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("authors", storyService.getAuthors());
        model.addAttribute("stories", storyService.findAllStories());
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @RequestMapping("/filter/{author:.+}")
    public String filter(@PathVariable(value = "author") final String author, Model model) {
        model.addAttribute("authors", storyService.getAuthors());
        model.addAttribute("stories", storyService.findAllStoriesByAuthor(author));
        return "index";
    }

    @RequestMapping("/story/{id}")
    public String dictation(@PathVariable("id") final Integer id, Model model) {
        model.addAttribute("story", storyService.findStory(id));
        return "story";
    }

    @RequestMapping(value = {"/admin"})
    public String admin(Model model) {
        model.addAttribute("stories", storyService.findAllStories());
        return "admin";
    }

    @RequestMapping("/admin/edit/{id}")
    public String editStory(@PathVariable("id") final Integer id, Model model) {
        model.addAttribute("story", storyService.findStory(id));
        return "storyform";
    }

    @RequestMapping("/admin/delete/{id}")
    public String deleteStory(@PathVariable("id") final Integer id, Model model) {
        storyService.deleteStory(id);
        model.addAttribute("stories", storyService.findAllStories());
        return "admin";
    }

    @RequestMapping("/admin/new/story")
    public String newStory(Model model) {
        model.addAttribute("story", new Story());
        return "storyform";
    }

    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    public HttpEntity<byte[]> getImage(@PathVariable("id") final Integer id) {
        byte[] bytes = storyService.findStory(id).getImage();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new HttpEntity<>(bytes, headers);
    }

    @RequestMapping(value = "/audio/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public HttpEntity<byte[]> getAudio(@PathVariable("id") final Integer id) {
        byte[] bytes = storyService.findStory(id).getAudio();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("audio", "mpeg"));
        return new HttpEntity<>(bytes, headers);
    }

    @RequestMapping(value = "/admin/save/story", method = RequestMethod.POST)
    public String saveStory(Story story, @RequestParam("file") MultipartFile file, Model model) {
        if (story.getName() == null || story.getName().equals("")){
            model.addAttribute("message", "Введите название.");
            model.addAttribute("story", story);
            return "storyform";
        }

        if (story.getAuthor() == null || story.getAuthor().equals("")){
            model.addAttribute("message", "Введите автора.");
            model.addAttribute("story", story);
            return "storyform";
        }

        if (story.getContent() == null || story.getContent().equals("")){
            model.addAttribute("message", "Введите текст.");
            model.addAttribute("story", story);
            return "storyform";
        }

        if (story.getId() == null && file.isEmpty()){
            model.addAttribute("message", "Выбирите изображение.");
            model.addAttribute("story", story);
            return "storyform";
        }

        try {
            if (!file.isEmpty())
                story.setImage(file.getBytes());

            if (story.getId() == null || !story.getContent().equals(storyService.findStory(story.getId()).getContent()))
                story.setAudio(yandexSpeechClient.getAudio(story.getContent()));

            storyService.saveStory(story);
        } catch (IOException e) {
        }

        return "redirect:/admin";
    }

}
