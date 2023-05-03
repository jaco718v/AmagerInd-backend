package dat3.amager_records_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String img;
    private String textField;
    private String headline;
    /*
    private Vinyl vinyl;
    private Event event;


    public News(String img, String textField, String headline, Vinyl vinyl){
        this.img = img;
        this.textField = textField;
        this.headline = headline;
        this.vinyl = vinyl;
    }


    public News(String img, String textField, String headlineEvent event){
        this.img = img;
        this.textField = textField;
        this.headline = headline;
        this.event = event;
    }
    */
    public News(String img, String textField, String headline){
        this.img = img;
        this.textField = textField;
        this.headline = headline;

    }



}
