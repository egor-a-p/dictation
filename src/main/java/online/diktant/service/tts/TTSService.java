package online.diktant.service.tts;


import online.diktant.util.exception.TTSException;

public interface TTSService {
    byte[] generate(String text) throws TTSException;
}
