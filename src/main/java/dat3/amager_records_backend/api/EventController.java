package dat3.amager_records_backend.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.amager_records_backend.dto.EventRequest;
import dat3.amager_records_backend.dto.EventResponse;
import dat3.amager_records_backend.service.EventService;
import jakarta.annotation.security.PermitAll;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/events/")
public class EventController {


  private EventService eventService;

  public EventController(EventService eventService){
    this.eventService=eventService;
  }

  @GetMapping("total")
  public long getEventsTotal(){
    return eventService.getEventsTotal();
  }


  @GetMapping("{id}")
  public EventResponse getEventById(@PathVariable long id){
    return eventService.getEventById(id);
  }


  @GetMapping("short")
  public List<EventResponse> getAllEventsShort(Pageable pageable){
    return eventService.getAllEventsShort();
  }

  @GetMapping
  public List<EventResponse> getAllEvents(Pageable pageable){
    return eventService.getAllEvents(pageable);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PostMapping
  public EventResponse createEvent(MultipartHttpServletRequest request){
    return eventService.createEvent(request);
  }
  @PreAuthorize("hasAuthority('ADMIN')")
  @PutMapping("{id}")
  public EventResponse updateEvent(MultipartHttpServletRequest request,
                                   @PathVariable long id){
    return eventService.updateEvent(request,id);
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteEvent(@PathVariable long id){
    return eventService.deleteEvent(id);
  }

}
