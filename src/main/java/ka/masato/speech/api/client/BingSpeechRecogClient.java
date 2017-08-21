package ka.masato.speech.api.client;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ka.masato.speech.api.client.interceptor.TokenHeaderInterceptor;
import ka.masato.speech.api.client.model.RecognitionResult;

@Component
public class BingSpeechRecogClient {
	@Value("${bing.speech.recognition.api:https://speech.platform.bing.com}/speech/recognition/{type}/cognitiveservices/v1?language={language}")
	String speechApiUri;
	
	private final RestTemplate restTemplate;
	
	
	public BingSpeechRecogClient(RestTemplateBuilder restTemplateBuilder,
								TokenHeaderInterceptor tokenHeaderInterceoptor){
		restTemplate = restTemplateBuilder.additionalInterceptors(tokenHeaderInterceoptor).build();
	}
	
	public RecognitionResult recognition(String sampleRate, String formatType, byte[] wave, String type,String language){
		URI targetUri = UriComponentsBuilder.fromUriString(speechApiUri).buildAndExpand(type,language).toUri();
		RequestEntity<byte[]> request = RequestEntity
							.post(targetUri)
							.header("Transfer-Encoding", "chunked")
							.header("Content-Type", "audio/wav; codec=\""+formatType+"\"; samplerate="+sampleRate)
							.body(wave);
		ResponseEntity<RecognitionResult> result = restTemplate.exchange(request,RecognitionResult.class);
		return result.getBody();
	}
	
}
