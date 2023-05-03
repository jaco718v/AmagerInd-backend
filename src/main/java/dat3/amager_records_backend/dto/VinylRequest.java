package dat3.amager_records_backend.dto;

import dat3.amager_records_backend.entity.Vinyl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VinylRequest {
  private String artist;
  private String title;
  private String country;
  private int year;
  private String label;
  private String genre;
  private String image;
  private int price;

  public static Vinyl getVinylFromRequest(VinylRequest vinylRequest) {
    return new Vinyl(vinylRequest.artist, vinylRequest.title, vinylRequest.country, vinylRequest.year, vinylRequest.label, vinylRequest.genre, vinylRequest.image, vinylRequest.price);
  }
}
