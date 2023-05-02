package dat3.amager_records_backend.repository;

import dat3.amager_records_backend.entity.Vinyl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VinylRepository extends JpaRepository<Vinyl, Long> {
}
