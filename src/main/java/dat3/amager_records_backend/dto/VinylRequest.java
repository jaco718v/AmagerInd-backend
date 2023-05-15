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

  public VinylRequest(String artist, String title, String country, int year, String label, String genre, String image, int price) {
    this.artist = artist;
    this.title = title;
    this.country = country;
    this.year = year;
    this.label = label;
    this.genre = genre;
    this.image = image;
    this.price = price;
  }
}
