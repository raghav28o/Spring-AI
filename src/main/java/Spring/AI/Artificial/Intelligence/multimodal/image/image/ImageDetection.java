package Spring.AI.Artificial.Intelligence.multimodal.image.image;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageDetection {
    private final ChatClient chatClient;

    @Value("classpath:/photos/photo-1631152186151-a0f9dde3a1e3.jpeg")
    Resource sampleImage;

    public ImageDetection(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/imageToText")
    public String imageToText() {
       return chatClient.prompt()
               .user(u -> {u.text("Describe the image");
                   u.media(MimeTypeUtils.IMAGE_JPEG, sampleImage);
               })
               .call()
               .content();
    }


}
