package by.smertex.core.util;

import by.smertex.core.database.model.impl.Link;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LinkGenerator {
    public Link generateShortLink(String fullLink){
        return Link.builder()
                .fullLink(fullLink)
                .shortLink(UUID.randomUUID().toString())
                .build();
    }
}
