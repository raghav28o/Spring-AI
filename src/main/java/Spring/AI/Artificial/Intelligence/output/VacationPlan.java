package Spring.AI.Artificial.Intelligence.output;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacationPlan {
    private final ChatClient chatClient;

    public VacationPlan(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/vacation/unstructured")
    public String unstructured(){
        return chatClient.prompt()
                .user("I want a trip in nainital. Give me a list of things to do")
                .call()
                .content();
    }


    // this gives json schema
    @GetMapping("/vacation/structured")
    public Itinerary structured(){
        return chatClient.prompt()
                .user("I want a trip in nainital. Give me a list of things to do")
                .call()
                .entity(Itinerary.class);
    }
}
