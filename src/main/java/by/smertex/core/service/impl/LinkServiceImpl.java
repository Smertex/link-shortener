package by.smertex.core.service.impl;

import by.smertex.core.database.model.impl.Link;
import by.smertex.core.database.repository.LinkRepository;
import by.smertex.core.dto.input.FullLinkDto;
import by.smertex.core.dto.output.ShortLinkDto;
import by.smertex.core.exception.FullLinkNotFoundException;
import by.smertex.core.service.LinkService;
import by.smertex.core.util.LinkGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;

    private final LinkGenerator linkGenerator;

    @Override
    @Transactional
    public ShortLinkDto shortenLink(FullLinkDto link) {
        return Optional.of(linkRepository.save(
                        linkGenerator.generateShortLink(link.fullLink())))
                .map(entity -> ShortLinkDto.builder()
                        .shortLink(
                                ServletUriComponentsBuilder.fromCurrentContextPath()
                                        .path("/link-shortener/{shortLink}")
                                        .buildAndExpand(entity.getShortLink())
                                        .toUriString()
                        )
                        .build()
                )
                .orElseThrow(() -> new FullLinkNotFoundException("Link save exception"));
    }

    @Override
    public String getRedirectUrl(String link) {
        return linkRepository.findByShortLink(link)
                .map(Link::getFullLink)
                .orElseThrow(() -> new FullLinkNotFoundException("Full link not found"));
    }
}
