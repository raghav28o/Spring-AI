package Spring.AI.Artificial.Intelligence.rag;

import org.springframework.core.io.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Configuration
public class RagConfiguration {

    private static final Logger Log = LoggerFactory.getLogger(RagConfiguration.class);
    private final String vectorStoreName = "vectorstore.json";


    @Value("classpath:/data/models.json")
    private Resource models;

    @Bean
    SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel) {
        var simpleVectorStore = SimpleVectorStore.builder(embeddingModel).build();
        File vectorStoreFile = getVectorStoreFile();
        if (vectorStoreFile.exists()) {
            Log.info("Vector store already exists at {}", vectorStoreFile.getAbsolutePath());
            simpleVectorStore.load(vectorStoreFile);
        } else {
            Log.info("Creating vector store at {}", vectorStoreFile.getAbsolutePath());
            TextReader textReader = new TextReader((org.springframework.core.io.Resource) models);
            textReader.getCustomMetadata().put("filename", "models.txt");
            List<Document> documents = textReader.get();
            TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
            List<org.springframework.ai.document.Document> splitDocuments = tokenTextSplitter.apply(documents);

            simpleVectorStore.add(splitDocuments);
            simpleVectorStore.save(vectorStoreFile);
        }
        return simpleVectorStore;
    }


    private File getVectorStoreFile() {
        Path path = Paths.get("src", "main", "resources", "data");
        String absolutePath = path.toFile().getAbsolutePath() + "/" + vectorStoreName;
        return new File(absolutePath);
    }
}
