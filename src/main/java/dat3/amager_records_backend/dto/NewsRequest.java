package dat3.amager_records_backend.dto;


import dat3.amager_records_backend.entity.EventEntity;
import dat3.amager_records_backend.entity.News;
import dat3.amager_records_backend.entity.Vinyl;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class NewsRequest {

    private byte[] img;
    private String textField;
    private String headline;
    private Long vinylId;
    private Long eventId;
    private int priority;


    public byte[] imageToByte(MultipartFile image){
        if(image != null){
            try{
                return image.getBytes();
            }
            catch (IOException e){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Fejl vedrørende billede");
            }
        }
        return null;
    }


    public News getNewsEntity(NewsRequest newsRequest,EventEntity event){
        return new News(newsRequest.getImg(), newsRequest.textField, newsRequest.headline, newsRequest.priority, event);
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
        this.priority = news.getPriority();
        /*
        if(news.getVinyl()!=null){
            this.vinyl = news.getVinyl();

        }
        if(news.getEvent() != null){
            this.event = news.getEvent();
        }

         */
    }
    public NewsRequest(MultipartHttpServletRequest r) {
        this.img = imageToByte(r.getFile("image"));
        this.textField = r.getParameter("textField").replaceAll("^\"|\"$", "");
        ;
        this.headline = r.getParameter("headline").replaceAll("^\"|\"$", "");
        ;
        this.priority = Integer.parseInt(r.getParameter("priority").replaceAll("^\"|\"$", ""));
        ;
        this.eventId = Long.parseLong(r.getParameter("event").replaceAll("^\"|\"$", ""));
    }
}
