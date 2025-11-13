package Spring.AI.Artificial.Intelligence.multimodal.image.image;

import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ImageGeneration {
    private final OpenAiImageModel imageModel;

    public ImageGeneration(OpenAiImageModel imageModel) {
        this.imageModel = imageModel;
    }

    @GetMapping("/generateImage")
    public ResponseEntity<Map<String, String>> generateImage(@RequestParam(defaultValue = "Mahadev giving blessing to boy, Raghav is printed on that boys, tshirt, diving lights coming from back of mahadev") String prompt) {
        ImageOptions options = OpenAiImageOptions.builder()
                .model("dall-e-3")
                .width(1024)
                .height(1024)
                .quality("standard")
                .style("vivid")
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(prompt, options);
        ImageResponse response = imageModel.call(imagePrompt);

        String url = response.getResult().getOutput().getUrl();

        return ResponseEntity.ok(Map.of(
        "prompt", prompt,
        "imageUrl", url
        ));
    }
}
