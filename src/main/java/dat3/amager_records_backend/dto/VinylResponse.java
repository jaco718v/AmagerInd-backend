package dat3.amager_records_backend.dto;

import dat3.amager_records_backend.entity.Vinyl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VinylResponse {

  private String artist;
  private String title;
  private String country;
  private int year;
  private String label;
  private String genre;
  private String image;
  private int price;

  public VinylResponse(Vinyl vinyl) {
    this.artist = vinyl.getArtist();
    this.title = vinyl.getTitle();
    this.country = vinyl.getCountry();
    this.year = vinyl.getYear();
    this.label = vinyl.getLabel();
    this.genre = vinyl.getGenre();
    this.image = vinyl.getImage();
    this.price = vinyl.getPrice();
  }
}
