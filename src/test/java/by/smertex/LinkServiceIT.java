package by.smertex;

import by.smertex.core.database.model.impl.Link;
import by.smertex.core.database.repository.LinkRepository;
import by.smertex.core.dto.input.FullLinkDto;
import by.smertex.core.dto.output.ShortLinkDto;
import by.smertex.core.service.LinkService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@Transactional
public class LinkServiceIT {

    @Autowired
    private LinkService linkService;

    @Autowired
    private LinkRepository linkRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:16.0");

    @BeforeEach
    void init(){
        postgreSQLContainer.start();

        Link link = Link.builder()
                .fullLink("full_link")
                .shortLink("short_link")
                .build();
        linkRepository.save(link);
    }

    @Test
    @DisplayName("Проверка на генерацию короткой ссылки")
    void generateShortLinkTest(){
        FullLinkDto fullLinkDto = new FullLinkDto("localhost:8080/link-shortener");
        ShortLinkDto shortenLink = linkService.shortenLink(fullLinkDto);
        Assertions.assertNotNull(shortenLink);
        Link savedLink = linkRepository.findByShortLink(shortenLink.shortLink())
                .orElse(null);
        Assertions.assertNotNull(savedLink);
        Assertions.assertNotNull(savedLink.getFullLink());
    }

    @Test
    @DisplayName("Проверка на получение ссылки")
    void generateLinkTest(){
        String fullLink = "full_link";
        String shortLink = "short_link";
        String returnedFullLink = linkService.getRedirectUrl(shortLink);
        Assertions.assertNotNull(returnedFullLink);
        Assertions.assertEquals(fullLink, returnedFullLink);
    }
}
