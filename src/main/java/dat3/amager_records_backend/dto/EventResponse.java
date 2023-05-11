package dat3.amager_records_backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.amager_records_backend.entity.EventEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.DayOfWeek;
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


  private String dateMonth;

  private String dayTime;

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
    this.dateMonth = dateMonthString(e.getDateTime());
    this.dayTime = dayTime(e.getDateTime());
    if(getFull){
      if(e.getImage() != null){
        this.encodedImage = Base64.getEncoder().encodeToString(e.getImage());
      }
    }
  }

  public String dateMonthString(LocalDateTime localDateTime) {
    int monthValue = localDateTime.getMonthValue();
    int dayOfMonth = localDateTime.getDayOfMonth();

    String month;

    if (monthValue == 1)  {
      month = "Januar";
    } else if (monthValue == 2) {
      month = "Februar";
    } else if (monthValue == 3) {
      month = "Marts";
    } else if (monthValue == 4) {
      month = "April";
    } else if (monthValue == 5) {
      month = "Maj";
    } else if (monthValue == 6) {
      month = "Juni";
    } else if (monthValue == 7) {
      month = "Juli";
    } else if (monthValue == 8) {
      month = "August";
    } else if (monthValue == 9) {
      month = "September";
    } else if (monthValue == 10) {
      month = "Oktober";
    } else if (monthValue == 11) {
      month = "November";
    } else {
      month = "December";
    }
    return dayOfMonth + ". " + month;
  }

  public String dayTime(LocalDateTime localDateTime) {
    int hour = localDateTime.getHour();
    int minute = localDateTime.getMinute();
    DayOfWeek weekday = localDateTime.getDayOfWeek();

    String danishWeekDay;

    if (weekday == DayOfWeek.MONDAY) {
      danishWeekDay = "Mandag";
    } else if (weekday == DayOfWeek.TUESDAY) {
      danishWeekDay = "Tirsdag";
    } else if (weekday == DayOfWeek.WEDNESDAY) {
      danishWeekDay = "Onsdag";
    } else if (weekday == DayOfWeek.THURSDAY) {
      danishWeekDay = "Torsdag";
    } else if (weekday == DayOfWeek.FRIDAY) {
      danishWeekDay = "Fredag";
    } else if (weekday == DayOfWeek.SATURDAY) {
      danishWeekDay = "Lørdag";
    } else {
      danishWeekDay = "Søndag";
    }

    return danishWeekDay + " kl. " + hour + "." + minute;

  }
}
