package Spring.AI.Artificial.Intelligence.memory;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Stack;

@RestController
public class MemoryController {
    private final ChatClient chatClient;


//    no memory
    public MemoryController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    // with memory
//    public MemoryController (ChatClient.Builder builder, ChatMemory chatMemory) {
//        this.chatClient = builder
//                .defaultAdvisors (MessageChatMemoryAdvisor.builder (chatMemory).build())
//                .build();
//    }


    @GetMapping("/memory" )
    public String memoryExample(@RequestParam String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }
}
