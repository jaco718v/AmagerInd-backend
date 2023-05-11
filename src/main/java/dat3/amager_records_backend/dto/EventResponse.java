package dat3.amager_records_backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.amager_records_backend.entity.EventEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;

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

  @JsonFormat(pattern = "yyyy-MM-dd")
  @CreationTimestamp
  private LocalDate created;

  private String encodedImage;

  public EventResponse(EventEntity e, boolean getFull) {
    this.id=e.getId();
    this.title = e.getTitle();
    this.description = e.getDescription();
    this.dateTime = e.getDateTime();
    this.created = e.getCreated();
    if(getFull){
      if(e.getImage() != null){
        this.encodedImage = Base64.getEncoder().encodeToString(e.getImage());
      }
    }
  }
}
