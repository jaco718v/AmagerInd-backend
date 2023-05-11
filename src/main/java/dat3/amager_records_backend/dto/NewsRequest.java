package dat3.amager_records_backend.dto;


import dat3.amager_records_backend.entity.EventEntity;
import dat3.amager_records_backend.entity.News;
import dat3.amager_records_backend.entity.Vinyl;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class NewsRequest {

    private String img;
    private String textField;
    private String headline;
    private Long vinyl;
    private Long event;



    public News getNewsEntity(NewsRequest newsRequest,EventEntity event){
        return new News(newsRequest.img, newsRequest.textField, newsRequest.headline,event);
    }
    /*
    public News getNewsEntity(NewsRequest newsRequest, Vinyl vinyl){
        return new News(newsRequest.img, newsRequest.textField, newsRequest.headline,vinyl);
    }
     */
    public NewsRequest(News news){
        this.img = news.getImg();
        this.textField = news.getTextField();
        this.headline = news.getHeadline();
        /*
        if(news.getVinyl()!=null){
            this.vinyl = news.getVinyl();

        }
        if(news.getEvent() != null){
            this.event = news.getEvent();
        }

         */
    }


}
