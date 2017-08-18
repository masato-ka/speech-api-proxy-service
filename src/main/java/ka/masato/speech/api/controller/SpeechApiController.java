package ka.masato.speech.api.controller;

import java.io.IOException;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
	@ApiOperation(value = "音声認識を実行数する")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "音声認識の結果取得"),
            @ApiResponse(code = 400, message = "該当するグループが存在しない。")
	}
    )
	public RecognitionResult speechRecognize(@Validated @RequestParam String formatType, 
											 @Validated @RequestParam Long sampleRate, 
											 @RequestBody MultipartFile audio) throws IOException{

		RecognitionResult result = speechRecognitionService.recognition(sampleRate.toString(), 
																		formatType, 
																		audio.getBytes());
		return result;
	}
}
