package info.egor_a_petrov.web;

import info.egor_a_petrov.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DictationController {
    private StoryService storyService;


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
}
