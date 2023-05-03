package dat3.amager_records_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Vinyl {

  @Id
  @GeneratedValue
  private long id;
  private String artist;
  private String title;
  private String country;
  private int year;
  private String label;
  private String genre;
  private String image;
  private int price;

  public Vinyl(String artist, String title, String country, int year, String label, String genre, String image, int price) {
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

