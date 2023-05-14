package dat3.amager_records_backend.service;

import dat3.amager_records_backend.dto.EventRequest;
import dat3.amager_records_backend.dto.EventResponse;
import dat3.amager_records_backend.entity.EventEntity;
import dat3.amager_records_backend.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class EventServiceTest {

  @Autowired
  public EventRepository eventRepository;

  public EventService eventService;

  boolean dataIsReady = false;

  @BeforeEach
  void setUp() {
    if(!dataIsReady) {
      eventRepository.save(new EventEntity("Danseturnering", "Der bliver danset", LocalDateTime.now()));
      eventRepository.save(new EventEntity("Vinyl-party", "Der er vinyl!", LocalDateTime.now().plusDays(1)));

      dataIsReady = true;
      eventService = new EventService(eventRepository);
    }
    }

  @Test
  void event_CRUD_Get() {
    List<EventEntity> eventList = eventRepository.findAll();


    assertSame(2, eventList.size());
  }

  @Test
  void event_CRUD_Create() {
    EventRequest req = new EventRequest("Jazz-fest", "Der bliver jazzet", LocalDateTime.now().plusHours(5));
    eventService.createEvent(req);
    List<EventEntity> eventList = eventRepository.findAll();
    assertEquals(3,eventList.size());
  }

  @Test
  void event_CRUD_Update() {
    EventRequest req = new EventRequest("Danse-Deathmatch", "Der bliver danset hårdt", LocalDateTime.now().plusHours(2));

    EventEntity event = eventRepository.findByTitle("Danseturnering"); // Fordi H2 ikke Resetter Id generation mellem @BeforeEach
    eventService.updateEvent(req,event.getId());

    assertSame(event.getTitle(),req.getTitle());
  }

  @Test
  void event_CRUD_Delete() {
    EventEntity event = eventRepository.findByTitle("Danseturnering"); // Fordi H2 ikke Resetter Id generation mellem @BeforeEach
    eventService.deleteEvent(event.getId());

    List<EventEntity> eventList = eventRepository.findAll();

    assertEquals(1,eventList.size());
  }
}