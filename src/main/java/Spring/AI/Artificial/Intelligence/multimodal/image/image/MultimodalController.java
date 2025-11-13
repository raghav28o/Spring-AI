package Spring.AI.Artificial.Intelligence.multimodal.image.image;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.content.Media;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/multimodal")
public class MultimodalController {

    private final ChatClient chatClient;

    public MultimodalController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping("/image-question")
    public String analyzeImage(
            @RequestParam("question") String question,
            @RequestPart("image") MultipartFile imageFile
    ) throws IOException {

        // Convert MultipartFile to Resource
        ByteArrayResource imageResource = new ByteArrayResource(imageFile.getBytes()) {
            @Override
            public String getFilename() {
                return imageFile.getOriginalFilename();
            }
        };

        // Create media object
        Media imageMedia = new Media(
                MimeTypeUtils.parseMimeType(imageFile.getContentType()),
                imageResource
        );

        // Use chatClient with prompt including both user text & media
        String response = chatClient.prompt()
                .user(u -> u.text(question)
                        .media(imageMedia))
                .call()
                .content();

        return response;
    }
}

