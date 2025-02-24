package by.smertex.api.controller;

import by.smertex.core.dto.input.FullLinkDto;
import by.smertex.core.dto.output.ShortLinkDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.view.RedirectView;

public interface LinkController {
    ResponseEntity<ShortLinkDto> shortenLink(FullLinkDto link);

    RedirectView redirectLink(String editedLink);
}
