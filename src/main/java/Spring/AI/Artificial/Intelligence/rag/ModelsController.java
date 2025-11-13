package Spring.AI.Artificial.Intelligence.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelsController {
    private final ChatClient chatClient;

    public ModelsController(ChatClient.Builder builder, VectorStore vectorStore) {
        this.chatClient = builder
                .defaultAdvisors((Advisor) new org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor(vectorStore))
                .build();
    }

    @GetMapping("/rag/models")
    public Models faq(@RequestParam(defaultValue = "Give me a list of all models from openAI along with their context windows") String question) {
        return chatClient.prompt()
                .user(question)
                .call()
                .entity(Models.class);
    }

}
