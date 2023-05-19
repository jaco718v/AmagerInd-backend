package dat3.amager_records_backend.service;

import dat3.amager_records_backend.dto.VinylRequest;
import dat3.amager_records_backend.dto.VinylResponse;
import dat3.amager_records_backend.entity.Vinyl;
import dat3.amager_records_backend.repository.VinylRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class VinylServiceTest {

  @Mock
  VinylRepository vinylRepository;

  VinylService vinylService;

  @BeforeEach
  void setUp() {
    vinylService = new VinylService(vinylRepository);
  }
  @Test
  void getVinyls() {
    Vinyl v1 = new Vinyl("David Bowie", "Hunky Dory", "US", 1972, "lb 5234", "Rock", "Billede", 1500);
    Vinyl v2 = new Vinyl("Pink Floyd", "The Wall", "Germany", 1983, "twv 4785", "Rock", "Billede", 150);
    Mockito.when(vinylRepository.findAll()).thenReturn(List.of(v1,v2));
    List<VinylResponse> vinyls = vinylService.getVinyls();
    assertEquals(2,vinyls.size());
  }

  @Test
  void getVinylByID() {
    Vinyl v1 = new Vinyl("David Bowie", "Hunky Dory", "US", 1972, "lb 5234", "Rock", "Billede", 1500);
    Mockito.when(vinylRepository.findById(1L)).thenReturn(java.util.Optional.of(v1));

    VinylResponse response = vinylService.getVinylByID(1);
    assertEquals("US",response.getCountry());
  }

  @Test
  void addVinyl() {
    Vinyl v1 = new Vinyl("David Bowie", "Hunky Dory", "US", 1972, "lb 5234", "Rock", "Billede", 1500);

    Mockito.when(vinylRepository.save(any(Vinyl.class))).thenReturn(v1);


    VinylRequest vinylRequest = new VinylRequest("David Bowie", "Hunky Dory", "US", 1972, "lb 5234", "Rock", "Billede", 1500);
    VinylResponse response = vinylService.addVinyl(vinylRequest);
    assertEquals("Hunky Dory",response.getTitle());
  }

  @Test
  void editVinyl() {
    Vinyl v1 = new Vinyl("David Bowie", "Hunky Dory", "US", 1972, "lb 5234", "Rock", "Billede", 1500);

    Mockito.when(vinylRepository.findById(1L)).thenReturn(Optional.of(v1));
    VinylRequest vinylRequest = new VinylRequest("Pink Floyd", "The Wall", "Germany", 1983, "twv 4785", "Rock", "Billede", 150);
    ResponseEntity<Boolean> response = vinylService.editVinyl(1, vinylRequest);
    ResponseEntity<Boolean> testResponse = new ResponseEntity<Boolean>(true, HttpStatus.OK);
    assertEquals(testResponse, response);
  }
}