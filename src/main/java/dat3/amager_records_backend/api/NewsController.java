package dat3.amager_records_backend.api;

import dat3.amager_records_backend.dto.NewsRequest;
import dat3.amager_records_backend.dto.NewsResponse;
import dat3.amager_records_backend.service.NewsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/news")
public class NewsController {

    NewsService newsService;

    public NewsController(NewsService newsService){
        this.newsService = newsService;
    }

    @GetMapping
    List<NewsResponse> getNews(){
        return newsService.getNews();
    }

    @GetMapping("{id}")
    NewsResponse getNewsById(@PathVariable long id) throws Exception {
        return newsService.findNewsById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    NewsResponse addNews(@RequestBody NewsRequest body){
        return newsService.addNews(body);
    }

    @PutMapping("{id}")
    public ResponseEntity<Boolean> editNews(@PathVariable long id, @RequestBody NewsRequest body) {
        newsService.editNews(body,id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("{id}")
    void deleteNewsById(@PathVariable long id) {
        newsService.deleteNewsById(id);
    }






}
