package dat3.amager_records_backend.service;

import dat3.amager_records_backend.dto.EventRequest;
import dat3.amager_records_backend.dto.EventResponse;
import dat3.amager_records_backend.entity.EventEntity;
import dat3.amager_records_backend.repository.EventRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    return new EventResponse(event, true);
  }

  public List<EventResponse> getAllEventsShort(){
    List<EventEntity> eventList  = eventRepository.findAllEventsShortened();
    List<EventResponse> eventResponseList = eventList.stream().map(n-> new EventResponse(n,true)).toList();
    return eventResponseList;
  }

  public List<EventResponse> getAllEvents(Pageable pageable){
    List<EventEntity> eventList  = eventRepository.findAll(pageable).getContent();
    List<EventResponse> eventResponseList = eventList.stream().map(n-> new EventResponse(n,true)).toList();
    return eventResponseList;
  }

  public EventRequest createEventRequestFromMulti(MultipartHttpServletRequest request){

    return new EventRequest(request);

  }

  public EventResponse createEvent(EventRequest req){
    EventEntity newEvent = new EventEntity(req);

    eventRepository.save(newEvent);

    return new EventResponse(newEvent, false);
  }


  public EventResponse updateEvent(EventRequest req, long id){

    EventEntity event = eventRepository.findById(id).get();

    if(req.getTitle() != null){
      event.setTitle(req.getTitle());
    }
    if(req.getDescription() != null){
      event.setDescription(req.getDescription());
    }
    if(req.getDateTime() != null){
      event.setDateTime(req.getDateTime());
    }
    if(req.getImage()!=null){
      event.setImage(req.getImage());
    }

    eventRepository.save(event);

    return new EventResponse(event,false);
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
