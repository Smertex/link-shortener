package by.smertex.core.database.model.impl;

import by.smertex.core.database.model.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "link", schema = "link_shortener")
@SequenceGenerator(name = "link_seq", sequenceName = "link_seq", allocationSize = 3)
public class Link implements AbstractEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "link_seq")
    private Long id;

    @Column(name = "full_link")
    private String fullLink;

    @Column(name = "short_link")
    private String shortLink;
}
