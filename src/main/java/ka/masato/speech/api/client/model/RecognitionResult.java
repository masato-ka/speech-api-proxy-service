package ka.masato.speech.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecognitionResult {
	
	@JsonProperty("RecognitionStatus")
	private String recognitionStatus;
	@JsonProperty("DisplayText")
	private String displayText;
	@JsonProperty("Offset")
	private Long offset;
	@JsonProperty("Duration")
	private Long duration;
}
