package dat3.amager_records_backend.repository;

import dat3.amager_records_backend.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {

    News findNewsById(long id);


}
