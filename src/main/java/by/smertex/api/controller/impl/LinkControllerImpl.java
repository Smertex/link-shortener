package by.smertex.api.controller.impl;

import by.smertex.api.controller.LinkController;
import by.smertex.core.dto.input.FullLinkDto;
import by.smertex.core.dto.output.ShortLinkDto;
import by.smertex.core.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/link-shortener")
@RequiredArgsConstructor
@Validated
public class LinkControllerImpl implements LinkController {

    private final LinkService linkService;

    @PostMapping
    public ResponseEntity<ShortLinkDto> shortenLink(@RequestBody FullLinkDto link) {
        return ResponseEntity.ok(linkService.shortenLink(link));
    }

    @GetMapping("/{editedLink}")
    public RedirectView redirectLink(@PathVariable String editedLink) {
        return new RedirectView(linkService.getRedirectUrl(editedLink));
    }
}
