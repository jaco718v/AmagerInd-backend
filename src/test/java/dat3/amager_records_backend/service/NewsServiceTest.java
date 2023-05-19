package dat3.amager_records_backend.service;

import dat3.amager_records_backend.dto.NewsRequest;
import dat3.amager_records_backend.dto.NewsResponse;
import dat3.amager_records_backend.entity.News;
import dat3.amager_records_backend.repository.NewsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class NewsServiceTest {

    @Autowired
    NewsRepository newsRepository;

    NewsService newsService;

    boolean dataIsReady = false;

    @BeforeEach
    void setUp() {
        if(!dataIsReady){
            News news1 = News.builder().priority(1).headline("HEADLINE1").textField("TEXTFIELD1").encodedImage("image1").build();
            News news2 = News.builder().priority(2).headline("HEADLINE2").textField("TEXTFIELD2").encodedImage("image2").build();
            newsRepository.save(news1);
            newsRepository.save(news2);

            dataIsReady=true;
            newsService= new NewsService(newsRepository,null);
        }
    }
    @Test
    void testGetTotal(){
        long total = newsService.getTotal();
        assertEquals(2,total);
    }
    @Test
    void testGetNews(){
        List<NewsResponse> list = newsService.getNews(Pageable.unpaged());
        assertEquals(2,list.size());
    }
    //@Test
    void testFindNewsById(){
        NewsResponse newsResponse = newsService.findNewsById(1);
        assertEquals("HEADLINE1",newsResponse.getHeadline());
    }
    @Test
    void testAddNews(){
        NewsRequest nr = NewsRequest.builder().headline("HEADLINE3").priority(3).textField("TEXTFIELD3").build();

        NewsResponse newsResponse = newsService.addNews(nr);

        assertEquals(3,newsResponse.getPriority());

    }
    @Test
    void testEditNews(){
        NewsRequest nr = NewsRequest.builder().headline("HEADLINE3").priority(3).textField("TEXTFIELD3").build();
        ResponseEntity<Boolean> responseEntity = new ResponseEntity<Boolean>(true, HttpStatus.OK);
        assertEquals(responseEntity,newsService.editNews(nr,2));
        assertEquals("HEADLINE3",newsRepository.findNewsById(2).getHeadline());
    }
    //@Test
    void testDeleteNews(){
        Long id = 2L;
        newsService.deleteNewsById(id);
        assertEquals(false,newsRepository.existsById(id));
    }
}