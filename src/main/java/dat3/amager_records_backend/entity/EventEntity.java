package dat3.amager_records_backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.amager_records_backend.dto.EventRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class EventEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String title;

  private String type;

  private String description;

  //Image?

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime dateTime;

  public EventEntity(EventRequest e) {
    this.title = e.getTitle();
    this.type = e.getType();
    this.description = e.getDescription();
    this.dateTime = e.getDateTime();
  }
}
