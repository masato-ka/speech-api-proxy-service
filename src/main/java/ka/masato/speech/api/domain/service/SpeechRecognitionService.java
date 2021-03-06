package ka.masato.speech.api.domain.service;
import org.springframework.stereotype.Service;

import ka.masato.speech.api.client.BingSpeechRecogClient;
import ka.masato.speech.api.client.model.RecognitionResult;


@Service
public class SpeechRecognitionService {

	private final BingSpeechRecogClient bingSpeechRecogClient;
	public SpeechRecognitionService(BingSpeechRecogClient bingSpeechRecogClient){
		this.bingSpeechRecogClient = bingSpeechRecogClient;
	}
	
	public RecognitionResult recognition(String sampleRate, String formatType, String lang, byte[] wave, String mode){
		RecognitionResult result = bingSpeechRecogClient.recognition(sampleRate, formatType, wave,  mode, lang);
	 	return result;
	}
	
}
