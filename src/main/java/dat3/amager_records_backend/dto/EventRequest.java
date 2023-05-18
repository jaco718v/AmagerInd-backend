package dat3.amager_records_backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class EventRequest {

  private String title;

  private String description;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime dateTime;

  private String encodedImage;

  /*
  public LocalDateTime stringToDate(String dateString){
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
      LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
      return dateTime;
    }catch (Exception e){
      System.out.println(e.getMessage());
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Der mangler en fuld dato");
    }
  }

  public byte[] imageToByte(MultipartFile image){
    if(image != null){
      try{
        return image.getBytes();
      }
      catch (IOException e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Fejl vedrørende billede");
      }
    }
    return null;
  }

  public EventRequest(MultipartHttpServletRequest r) {
    this.title = r.getParameter("title").replaceAll("^\"|\"$", "");;
    this.description = r.getParameter("description").replaceAll("^\"|\"$", "");;
    this.dateTime = stringToDate(r.getParameter("dateTime").replaceAll("^\"|\"$", ""));
    this.image = imageToByte(r.getFile("image"));
  }
  */

  public EventRequest(String title, String description, LocalDateTime dateTime) {
    this.title = title;
    this.description = description;
    this.dateTime = dateTime;
  }
}
