package dat3.amager_records_backend.service;

import dat3.amager_records_backend.dto.EventRequest;
import dat3.amager_records_backend.dto.EventResponse;
import dat3.amager_records_backend.entity.EventEntity;
import dat3.amager_records_backend.repository.EventRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

  EventRepository eventRepository;

  public EventService(EventRepository eventRepository){
    this.eventRepository=eventRepository;
  }

  public long getEventsTotal(){
    return eventRepository.count();
  }

  public EventResponse getEventById(long id){
    EventEntity event = eventRepository.findById(id).get();
    return new EventResponse(event);
  }

  public List<EventResponse> getAllEvents(Pageable pageable){
    List<EventEntity> eventList  = eventRepository.findAll(pageable).getContent();
    List<EventResponse> eventResponseList = eventList.stream().map(EventResponse::new).toList();
    return eventResponseList;
  }

  public EventResponse createEvent(EventRequest r){
    EventEntity newEvent = new EventEntity(r);
    eventRepository.save(newEvent);
    return new EventResponse(newEvent);
  }

  public EventResponse updateEvent(EventRequest r, long id){
    EventEntity event = eventRepository.findById(id).get();
    if(r.getTitle() != null){
      event.setTitle(r.getTitle());
    }
    if(r.getType() != null){
      event.setType(r.getType());
    }
    if(r.getDescription() != null){
      event.setDescription(r.getDescription());
    }
    if(r.getDateTime() != null){
      event.setDateTime(r.getDateTime());
    }

    eventRepository.save(event);

    return new EventResponse(event);
  }

  public ResponseEntity<String> deleteEvent(long id){
    if (!eventRepository.existsById(id)){
      return new ResponseEntity<>("Event with given Id does not exist", HttpStatus.BAD_REQUEST);
    }

    eventRepository.deleteById(id);

    if (eventRepository.existsById(id)){
      return new ResponseEntity<>("Event was not deleted due to an error",HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>("Event was deleted successfully",HttpStatus.OK);
  }
}
