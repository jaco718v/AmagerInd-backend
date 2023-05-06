package dat3.amager_records_backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.amager_records_backend.entity.News;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class NewsResponse {

    private long id;
    private String img;
    private String textField;
    private String headline;
    //private Vinyl vinyl;
    //private Event event;

    public NewsResponse(News news){
        this.id = news.getId();
        this.img = news.getImg();
        this.textField = news.getTextField();
        this.headline = news.getHeadline();
        /*
        if(news.getVinyl() != null){
            this.vinyl = news.getVinyl();
        }
        if(news.getEvent()!=null) {
            this.event = news.getEvent();
        }
        */


    }

}
