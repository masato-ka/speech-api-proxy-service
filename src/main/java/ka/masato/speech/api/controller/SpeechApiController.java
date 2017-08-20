package ka.masato.speech.api.controller;

import java.io.IOException;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ka.masato.speech.api.client.model.RecognitionResult;
import ka.masato.speech.api.domain.service.SpeechRecognitionService;

@RestController
@RequestMapping("/api/v1/speech/recognizer")
@Api(value="speechApiController")
@Validated
public class SpeechApiController {

	private final SpeechRecognitionService speechRecognitionService;
	
	static final String AUDIO_FORMAT_VALIDATION = "^audio/pcm$";
	static final String SAMPLING_VALIDATION = "^16000$|^44100$";
	static final String LANGUAGE_VALIDATION = "^en-US$|^ja-JP$";
	static final String MODE_VALIDATION = "^conversation$|^interactive$|^dictation$";
	
	public SpeechApiController(SpeechRecognitionService speechRecognitionService){
		this.speechRecognitionService = speechRecognitionService;
	}
	
	@PostMapping()
	@ApiOperation(value = "get result of speech recognition")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success speech recognition."),
            @ApiResponse(code = 400, message = "Same value validation wrong.")
	}
    )
	public RecognitionResult speechRecognize(@ApiParam(value="Required parameter. You must set the audio format. Now can set only audio/pcm.")
	                                         @RequestParam(required=true)@Valid @Pattern(regexp = AUDIO_FORMAT_VALIDATION)String formatType, 
											 @ApiParam(value="Required parameter. You must set the audio sampling rate. Now accepted values are 16000 or 44100.")
	                                         @RequestParam(required=true)@Valid @Pattern(regexp = SAMPLING_VALIDATION)String sampleRate,
	                                         @ApiParam(value="Option parameter. You can set the language. Default value is en-US. Other optional is ja-JP")
	                                         @RequestParam(required=false, defaultValue="en-US")@Valid @Pattern(regexp = LANGUAGE_VALIDATION)String lang,
											 @ApiParam(value="You must set audio file as PCM format and 16kHz sampling rate.")
	                                         @RequestBody MultipartFile audio,
	                                         @ApiParam(value="Option parameter. You can set the recognition type. Default value is conversation.")
    										 @RequestParam(required=false, defaultValue="conversation")@Valid @Pattern(regexp = MODE_VALIDATION)String mode
    										 ) throws IOException{

		RecognitionResult result = speechRecognitionService.recognition(sampleRate, 
																		formatType,
														                lang,
																		audio.getBytes(),
																		mode);
		return result;
	}
}
