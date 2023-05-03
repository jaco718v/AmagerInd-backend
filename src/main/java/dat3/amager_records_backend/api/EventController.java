package dat3.amager_records_backend.api;

import dat3.amager_records_backend.dto.EventRequest;
import dat3.amager_records_backend.dto.EventResponse;
import dat3.amager_records_backend.service.EventService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  public EventResponse getEventById(Pageable pageable, @PathVariable long id){
    return eventService.getEventById(id);
  }

  @GetMapping
  public List<EventResponse> getAllEvents(Pageable pageable){
    return eventService.getAllEvents(pageable);
  }

  @PostMapping
  public EventResponse createEvent(@RequestBody EventRequest body){
    return eventService.createEvent(body);
  }

  @PutMapping("{id}")
  public EventResponse updateEvent(@RequestBody EventRequest body, @PathVariable long id){
    return eventService.updateEvent(body,id);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteEvent(@PathVariable long id){
    return eventService.deleteEvent(id);
  }

}
