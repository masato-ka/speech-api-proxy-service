package ka.masato.speech.api.client;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import ka.masato.speech.api.client.interceptor.TokenHeaderInterceptor;
import ka.masato.speech.api.client.model.RecognitionResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BingSpeechRecogClient {
	@Value("${bing.speech.recognition.api:https://speech.platform.bing.com}/speech/recognition/{type}/cognitiveservices/v1?language={language}")
	String speechApiUri;
	
	private final RestTemplate restTemplate;
	
	private final String token="";
	
	public BingSpeechRecogClient(RestTemplateBuilder restTemplateBuilder,
								TokenHeaderInterceptor tokenHeaderInterceoptor){
		restTemplate = restTemplateBuilder.additionalInterceptors(tokenHeaderInterceoptor).build();
	}
	
	public RecognitionResult recognition(String sampleRate, String formatType, byte[] wave, String type,String language){
		URI targetUri = UriComponentsBuilder.fromUriString(speechApiUri).buildAndExpand(type,language).toUri();
		log.info(targetUri.toString());
		RequestEntity<byte[]> request = RequestEntity
							.post(targetUri)
							.header("Transfer-Encoding", "chunked")
							.header("Content-Type", "audio/wav; codec=\""+formatType+"\"; samplerate="+sampleRate)
							.body(wave);
		ResponseEntity<RecognitionResult> result = restTemplate.exchange(request,RecognitionResult.class);
		return result.getBody();
	}
	
}
