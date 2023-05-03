package dat3.amager_records_backend.dto;


import dat3.amager_records_backend.entity.News;
import lombok.*;
@Getter
@Setter

public class NewsRequest {

    private String img;
    private String textField;
    private String headline;
    //private Vinyl vinyl;
    //private Event event;


    public static News getNewsEntity(NewsRequest newsRequest){

        return new News(newsRequest.img, newsRequest.textField, newsRequest.headline/*,newsRequest.vinyl, newsRequest.event*/);
    }

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
