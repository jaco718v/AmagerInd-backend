package dat3.amager_records_backend.entity;

import dat3.amager_records_backend.dto.NewsRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "encoded_image", length = 16777215, columnDefinition = "mediumtext")
    private String encodedImage;
    private String textField;
    private String headline;
    private int priority;
    @OneToOne
    private EventEntity event;

    /*
    @OneToMany(mappedBy = "news",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Vinyl> vinyls;

    public void addVinyl(Vinyl v){
        if(vinyls ==null){
            vinyls =new ArrayList<>();
        }
        vinyls.add(v);
    }
      public News(String img, String textField, String headline, Vinyl vinyl){
        this.img = img;
        this.textField = textField;
        this.headline = headline;
        this.vinyl = vinyl;
    }
     */

    public News(NewsRequest r){
        this.encodedImage = r.getEncodedImage();
        this.textField = r.getTextField();
        this.headline = r.getHeadline();
        this.priority = r.getPriority();
    }

    public News(String encodedImage, String textField, String headline, int priority){
        this.encodedImage = encodedImage;
        this.textField = textField;
        this.headline = headline;
        this.priority = priority;

    }



}
