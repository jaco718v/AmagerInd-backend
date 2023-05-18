package dat3.amager_records_backend.api;

import dat3.amager_records_backend.dto.NewsRequest;
import dat3.amager_records_backend.dto.NewsResponse;
import dat3.amager_records_backend.service.NewsService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/news/")
public class NewsController {

    NewsService newsService;

    public NewsController(NewsService newsService){
        this.newsService = newsService;
    }

    @GetMapping("total")
    public long getTotal(){
        return newsService.getTotal();
    }

    @GetMapping
    List<NewsResponse> getNews(Pageable pageable){
        return newsService.getNews(pageable);
    }

    @GetMapping("{id}")
    NewsResponse getNewsById(@PathVariable long id) throws Exception {
        return newsService.findNewsById(id);
    }

    @PostMapping()
    NewsResponse addNews(@RequestBody NewsRequest body){
        return newsService.addNews(body);
    }

    @PutMapping("{id}")
    public ResponseEntity<Boolean> editNews(@PathVariable long id, @RequestBody NewsRequest body) {
        newsService.editNews(body,id);
        return ResponseEntity.ok(true);
    }
    /*
    @PostMapping("vinyl/{id}")
    public NewsResponse addVinylToNews(@PathVariable long id){
        return null;
    }
    @PostMapping("event/{id}")
    public NewsResponse addEventToNews(@PathVariable long id){
        return null;
    }

     */

    @DeleteMapping("{id}")
    void deleteNewsById(@PathVariable long id) {
        newsService.deleteNewsById(id);
    }






}
