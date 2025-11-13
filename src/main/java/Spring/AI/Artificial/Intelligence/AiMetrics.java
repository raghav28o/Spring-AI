//package Spring.AI.Artificial.Intelligence;
//
//import io.micrometer.core.instrument.Counter;
//import io.micrometer.core.instrument.MeterRegistry;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AiMetrics {
//
//    private final Counter promptCounter;
//    private final Counter tokensIn;
//    private final Counter tokensOut;
//
//    public AiMetrics(MeterRegistry registry) {
//
//        this.promptCounter = Counter.builder("ai_prompts_total")
//                .description("Total number of AI prompts sent")
//                .register(registry);
//
//        this.tokensIn = Counter.builder("gen_ai_tokens_in_total")
//                .description("Total prompt tokens sent")
//                .register(registry);
//
//        this.tokensOut = Counter.builder("gen_ai_tokens_out_total")
//                .description("Total output tokens received")
//                .register(registry);
//    }
//
//    public void incrementPromptCount() {
//        promptCounter.increment();
//    }
//
//    public void recordTokens(int in, int out) {
//        tokensIn.increment(in);
//        tokensOut.increment(out);
//    }
//}
//
