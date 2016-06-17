package online.diktant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class YandexSpeechClient {
    private RestTemplate restTemplate;

    @Value("${apiKey}")
    private String apiKey;

    @Autowired
    public YandexSpeechClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public byte[] getAudio(String content) {
        return restTemplate.getForObject(getURI(content), byte[].class);
    }

    private URI getURI(String content) {
        return UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("tts.voicetech.yandex.net")
                .path("/generate")
                .queryParam("format", "mp3")
                .queryParam("lang", "ru-RU")
                .queryParam("speaker", getSpeaker())
                .queryParam("emotion", "good")
                .queryParam("key", apiKey)
                .queryParam("text", content)
                .build().encode().toUri();
    }

    private String getSpeaker() {
        switch (ThreadLocalRandom.current().nextInt() % 4) {
            case 0:
                return "jane";
            case 1:
                return "ermil";
            case 2:
                return "omazh";
            default:
                return "zahar";
        }
    }
}