package Spring.AI.Artificial.Intelligence.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankServer {
    private final ChatClient chatClient;

       public BankServer(@Qualifier("openAIChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        var systemInstructions = """
         You are a helpful banking assistant.
         You can only discuss about information about banking services, account management, and financial advice.
         Always prioritize only banking related questions.
         If the question is not related to banking, politely inform the user that you can only assist with banking-related inquiries.""";
        return chatClient.prompt()
                .user(message)
                .system(systemInstructions)
                .call()
                .content();
    }
}
