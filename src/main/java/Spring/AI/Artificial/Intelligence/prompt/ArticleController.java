package Spring.AI.Artificial.Intelligence.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {
    private final ChatClient chatClient;

    public ArticleController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/posts/new")
    public String newPost(@RequestParam(value = "topic", defaultValue = "JDK Virtual Threads") String topic){
        var System  = """
               Blog post guidelines -
               
                1- under 500 words
                2- beginner friendly content
               """;

        return chatClient.prompt()
        .user( u ->{
        u.text("write me a blog post about topic {topic}");
        u.param("topic", topic);
    })
                .system(System)
                .call()
                .content();
    }
}
