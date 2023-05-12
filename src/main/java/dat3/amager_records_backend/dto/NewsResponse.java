package dat3.amager_records_backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.amager_records_backend.entity.News;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Base64;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class NewsResponse {

    private long id;
    private String encodedImage;
    private String textField;
    private String headline;
    //private Vinyl vinyl;
    //private Event event;

    public NewsResponse(News news, boolean getImage){
        this.id = news.getId();
        this.textField = news.getTextField();
        this.headline = news.getHeadline();
        if(getImage){
            if(news.getImg() != null){
                this.encodedImage = Base64.getEncoder().encodeToString(news.getImg());
            }
        }
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
