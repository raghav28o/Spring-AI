package Spring.AI.Artificial.Intelligence.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {


    private final ChatClient chatClient;

    public ChatController(@Qualifier("openAIChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/stream")
    public Flux<String> streamJoke(@RequestParam(value = "message", defaultValue = "i am visiting bareilly, tell me best places to visit") String message) {
        return chatClient.prompt()
                .user(message)
                .stream()
                .content();
    }

    @GetMapping("/")
    public String joke(@RequestParam(value = "message", defaultValue = "Tell me a dad joke about Dogs") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content(); // short for getResult().getOutput().getContent();
    }

    // return output only string
    @GetMapping("/jokes-by-topic")
    public String jokesByTopic(@RequestParam String topic) {
        return chatClient.prompt()
                .user(u -> u.text("Tell me a joke about {topic}").param("topic",topic))
                .call()
                .content();
    }

    // return full response object
    @GetMapping("jokes-with-response")
    public ChatResponse jokeWithResponse(@RequestParam(value = "message", defaultValue = "Tell me a dad joke about computers") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .chatResponse();
    }
}

//logging what we sre
// sending and receiving
