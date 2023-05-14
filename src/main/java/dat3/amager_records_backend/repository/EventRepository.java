package dat3.amager_records_backend.repository;

import dat3.amager_records_backend.entity.EventEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity,Long> {


  @Query(value = "SELECT id,title,description, date_time,created, null AS 'image' FROM event_entity", nativeQuery=true)
  List<EventEntity> findAllEventsShortened();

  EventEntity findByTitle(String title);

}
