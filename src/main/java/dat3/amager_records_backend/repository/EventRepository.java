package dat3.amager_records_backend.repository;

import dat3.amager_records_backend.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity,Long> {
  EventEntity findByTitle(String title);
}
