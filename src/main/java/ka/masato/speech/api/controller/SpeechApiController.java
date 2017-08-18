package ka.masato.speech.api.controller;

import java.io.IOException;

import javax.validation.Valid;
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
public class SpeechApiController {

	private final SpeechRecognitionService speechRecognitionService;
	
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
	public RecognitionResult speechRecognize(@ApiParam(value="Required parameter. You must set the audio format. Now can set only audio/wav.")
	                                         @RequestParam(required=true)String formatType, 
											 @ApiParam(value="Required parameter. You must set the audio sampling rate. for ecample 16000.")
	                                         @RequestParam(required=true)Long sampleRate, 
											 @ApiParam(value="You must set audio file as PCM format and 16kHz sampling rate.")
	                                         @RequestBody MultipartFile audio) throws IOException{

		RecognitionResult result = speechRecognitionService.recognition(sampleRate.toString(), 
																		formatType, 
																		audio.getBytes());
		return result;
	}
}
