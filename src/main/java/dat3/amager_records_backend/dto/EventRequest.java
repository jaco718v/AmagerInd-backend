package dat3.amager_records_backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
public class EventRequest {

  private String title;

  private String description;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime dateTime;

  public EventRequest(String title, String description, LocalDateTime dateTime) {
    this.title = title;
    this.description = description;
    this.dateTime = dateTime;
  }
}
