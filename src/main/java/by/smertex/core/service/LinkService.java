package by.smertex.core.service;

import by.smertex.core.dto.input.FullLinkDto;
import by.smertex.core.dto.output.ShortLinkDto;

public interface LinkService {
    ShortLinkDto shortenLink(FullLinkDto link);

    String getRedirectUrl(String link);
}
