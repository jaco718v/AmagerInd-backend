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

  public List<EventResponse> getAllEvents(Pageable pageable){
    List<EventEntity> eventList  = eventRepository.findAll(pageable).getContent();
    List<EventResponse> eventResponseList = eventList.stream().map(n-> new EventResponse(n,false)).toList();
    return eventResponseList;
  }

  public EventResponse createEvent(MultipartFile image, String title, String description, String dateTimeString){

    title = title.substring(1,title.length()-1);
    description = description.substring(1,description.length()-1);
    dateTimeString = dateTimeString.substring(1,dateTimeString.length()-1);


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

    EventRequest r = new EventRequest(title,description,dateTime);
    byte[] imageInBytes = null;
    try{
       imageInBytes = image.getBytes();
    }
    catch (IOException e){

    }
    EventEntity newEvent = new EventEntity(r);
    newEvent.setImage(imageInBytes);
    eventRepository.save(newEvent);
    return new EventResponse(newEvent, false);
  }

  public EventResponse updateEvent(EventRequest r, long id){
    EventEntity event = eventRepository.findById(id).get();
    if(r.getTitle() != null){
      event.setTitle(r.getTitle());
    }
    if(r.getDescription() != null){
      event.setDescription(r.getDescription());
    }
    if(r.getDateTime() != null){
      event.setDateTime(r.getDateTime());
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
