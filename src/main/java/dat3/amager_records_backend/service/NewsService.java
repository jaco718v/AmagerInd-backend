package dat3.amager_records_backend.service;


import dat3.amager_records_backend.dto.NewsRequest;
import dat3.amager_records_backend.dto.NewsResponse;
import dat3.amager_records_backend.entity.EventEntity;
import dat3.amager_records_backend.entity.News;
import dat3.amager_records_backend.repository.EventRepository;
import dat3.amager_records_backend.repository.NewsRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    NewsRepository newsRepository;
    EventRepository eventRepository;

    public NewsService(NewsRepository newsRepository,EventRepository eventRepository){
        this.newsRepository = newsRepository;
        this.eventRepository=eventRepository;
    }

    /*
    public NewsRequest makeNewsRequestFromMulti(MultipartHttpServletRequest req){

        return new NewsRequest(req);
    }
    */

    public long getTotal(){
        return newsRepository.count();
    }

    public List<NewsResponse> getNews(Pageable pageable) {

        List<News> news1 = newsRepository.findAll(pageable).getContent();
        List<NewsResponse> newsResponse = news1.stream().map(news -> new NewsResponse(news,true)).toList();

        return newsResponse;

    }

    public News findNews(long id) {

        News news = newsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "News with this ID doesnt exist"));

        return news;
    }

    public NewsResponse findNewsById(long id){
        News news1 = findNews(id);
        NewsResponse newsResponse = new NewsResponse(news1,true);
        return  newsResponse;
    }

    public NewsResponse addNews(NewsRequest r) {
        if(r.getHeadline()==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Nyhed skal have overskrift");
        }

        News newNews = new News(r);
        if(r.getEventId()!=null){
            EventEntity event = eventRepository.findById(r.getEventId()).orElseThrow();
            newNews.setEvent(event);
        }
        newNews = newsRepository.save(newNews);
        return new NewsResponse(newNews,false);
    }

    public ResponseEntity<Boolean> editNews(NewsRequest body, long id) {

        News newsToEdit =  findNews(id);
        Optional.ofNullable(body.getEncodedImage()).ifPresent(newsToEdit::setEncodedImage);
        Optional.ofNullable(body.getTextField()).ifPresent(newsToEdit::setTextField);
        Optional.ofNullable(body.getHeadline()).ifPresent(newsToEdit::setHeadline);
        //Optional.ofNullable(body.getVinyl()).ifPresent(newsToEdit::setVinyl);
        //Optional.ofNullable(body.getEvent()).ifPresent(newsToEdit::setEvent);
        newsRepository.save(newsToEdit);


        return new ResponseEntity(true, HttpStatus.OK);
    }

    public void deleteNewsById(long id){
        News news = findNews(id);
        newsRepository.delete(news);
    }









}
