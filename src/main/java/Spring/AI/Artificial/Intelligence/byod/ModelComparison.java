package Spring.AI.Artificial.Intelligence.byod;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelComparison {
    private final ChatClient chatClient;

    public ModelComparison(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/models")
    public String models () {
        return chatClient.prompt()
                .user("Can you give me an up to date list popular large language models and their current context windows?")
                .call()
                .content();
    }
}
