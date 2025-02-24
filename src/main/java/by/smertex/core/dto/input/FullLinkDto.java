package by.smertex.core.dto.input;

import by.smertex.core.util.Templates;
import jakarta.validation.constraints.Pattern;

public record FullLinkDto(@Pattern(regexp = Templates.URL_PATTERN) String fullLink) {
}
