package dat3.amager_records_backend.service;


import dat3.amager_records_backend.dto.NewsRequest;
import dat3.amager_records_backend.dto.NewsResponse;
import dat3.amager_records_backend.entity.News;
import dat3.amager_records_backend.repository.NewsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository){
        this.newsRepository = newsRepository;
    }

    public List<NewsResponse> getNews() {

        List<News> news1 = newsRepository.findAll();
        List<NewsResponse> newsResponse = news1.stream().map(news -> new NewsResponse(news)).toList();

        return newsResponse;

    }

    public News findNews(long id) {

        News news = newsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with this ID doesnt exist"));

        return news;
    }

    public NewsResponse findNewsById(long id){
        News news1 = findNews(id);
        NewsResponse newsResponse = new NewsResponse(news1);
        return  newsResponse;
    }

    public NewsResponse addNews(NewsRequest newsRequest) {

        News newNews = NewsRequest.getNewsEntity(newsRequest);
        newNews = newsRepository.save(newNews);

        return new NewsResponse(newNews);
    }

    public ResponseEntity<Boolean> editNews(NewsRequest body, long id) {

        News newsToEdit =  findNews(id);
        Optional.ofNullable(body.getImg()).ifPresent(newsToEdit::setImg);
        Optional.ofNullable(body.getTextField()).ifPresent(newsToEdit::setTextField);
        Optional.ofNullable(body.getHeadline()).ifPresent(newsToEdit::setHeadline);
        //Optional.ofNullable(body.getVinyl()).ifPresent(newsToEdit::setVinyl);
        //Optional.ofNullable(body.getEvent()).ifPresent(newsToEdit::setEvent);


        return new ResponseEntity(true, HttpStatus.OK);
    }

    public void deleteNewsById(long id){
        News news = findNews(id);
        newsRepository.delete(news);
    }









}