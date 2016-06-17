package online.diktant.service.tts;

import online.diktant.util.exception.TTSException;
import org.springframework.stereotype.Service;

@Service(value = "TTSService")
public class YandexTTSClient implements TTSService{

    @Override
    public byte[] generate(String text) throws TTSException {
        return new byte[0];
    }
}
