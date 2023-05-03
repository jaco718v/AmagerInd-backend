package dat3.amager_records_backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.amager_records_backend.entity.EventEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
public class EventResponse {

  private Long id;

  private String title;

  private String type;

  private String description;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime dateTime;

  public EventResponse(EventEntity e) {
    this.id=e.getId();
    this.title = e.getTitle();
    this.type = e.getType();
    this.description = e.getDescription();
    this.dateTime = e.getDateTime();
  }
}
