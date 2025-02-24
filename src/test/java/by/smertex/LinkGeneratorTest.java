package by.smertex;

import by.smertex.core.database.model.impl.Link;
import by.smertex.core.util.LinkGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = LinkGenerator.class)
public class LinkGeneratorTest {

    @Autowired
    private LinkGenerator linkGenerator;

    @Test
    @DisplayName("Тест создания сущности с ассоциацией")
    void generateLinkTest(){
        String rootPath = "localhost:8080/link-shortener";
        Link link = linkGenerator.generateShortLink(rootPath);
        Assertions.assertNull(link.getId());
        Assertions.assertNotNull(link.getFullLink());
        Assertions.assertNotNull(link.getShortLink());
    }
}
