package Spring.AI.Artificial.Intelligence;

//import org.springframework.ai.anthropic.AnthropicChatModel;
import Spring.AI.Artificial.Intelligence.mcp.SessionTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.List;

@SpringBootApplication
public class ArtificialIntelligenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtificialIntelligenceApplication.class, args);
	}

	@Bean
	@Primary
	public ChatClient openAIChatClient(OpenAiChatModel chatModel) {
        return ChatClient.create(chatModel);
    }

//    @Bean
//    public ChatClient anthropicChatClient(AnthropicChatModel chatModel) {
//        return ChatClient.create(chatModel);
//    }


    @Bean
    public List<ToolCallback> springIOSessionTools(SessionTools sessionTools) {
        return List.of(ToolCallbacks.from(sessionTools));
    }

}
