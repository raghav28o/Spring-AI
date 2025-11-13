package Spring.AI.Artificial.Intelligence.multimodal.image.audio;

import org.apache.catalina.connector.Request;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.api.ResponseFormat;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AudioGeneration {
    private final OpenAiAudioSpeechModel speechModel;

    public AudioGeneration(OpenAiAudioSpeechModel speechModel) {
        this.speechModel = speechModel;
    }

    @GetMapping("/speak")
    public ResponseEntity<byte[]> generateAudio(@RequestParam(defaultValue = "Hello, welcome to Spring AI with OpenAI audio generation capabilities!") String text) {
        var options = OpenAiAudioSpeechOptions.builder()
                .model("tts-1-hd") // or "tts-1-hd" for higher quality
                .voice (OpenAiAudioApi. SpeechRequest. Voice.ALLOY) //Selects a built-in voice
                .responseFormat (OpenAiAudioApi. SpeechRequest. AudioResponseFormat.MP3) //Output audio will be MP3
                .speed (1.0f) // Playback speed (normal). Could be 0.25 → slow, 4.0 → fast
                .build();


        //Wraps the text + configuration into a request
        //Sends it to OpenAI
        //Gets response containing the audio
        SpeechPrompt speechPrompt = new SpeechPrompt(text, options);
        SpeechResponse speechResponse = speechModel.call(speechPrompt);

        //Output is raw MP3 data in byte array
        byte[] audioBytes = speechResponse.getResult().getOutput();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "audio/mpeg") //audio/mpeg → correct MIME type for MP3
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"speech.mp3\"") //forces browser to download file with name speech.mp3
                .body (audioBytes); //MP3 data
    }
}
