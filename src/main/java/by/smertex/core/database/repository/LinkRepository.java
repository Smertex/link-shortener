package by.smertex.core.database.repository;

import by.smertex.core.database.model.impl.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    Optional<Link> findByShortLink(String shortLink);
}
