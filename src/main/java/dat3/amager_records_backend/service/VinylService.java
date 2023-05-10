package dat3.amager_records_backend.service;

import dat3.amager_records_backend.dto.VinylRequest;
import dat3.amager_records_backend.dto.VinylResponse;
import dat3.amager_records_backend.entity.Vinyl;
import dat3.amager_records_backend.repository.VinylRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VinylService {

  private VinylRepository vinylRepository;

  public VinylService(VinylRepository vinylRepository) {
    this.vinylRepository = vinylRepository;
  }

  public List<VinylResponse> getVinyls() {
    List<Vinyl> vinyls = vinylRepository.findAll();

    List<VinylResponse> vinylResponses = vinyls.stream().map(v -> new VinylResponse(v)).toList();
    return vinylResponses;
  }

  public VinylResponse getVinylByID(long id) {
    Vinyl vinyl = vinylRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vinyl with this id does not exist"));
    return new VinylResponse(vinyl);
  }

  public VinylResponse addVinyl(VinylRequest vinylRequest) {

    Vinyl newVinyl = vinylRequest.getVinylFromRequest(vinylRequest);
    vinylRepository.save(newVinyl);
    return new VinylResponse(newVinyl);
  }

  public ResponseEntity<Boolean> editVinyl(long id, VinylRequest vinylRequest) {
    Vinyl vinylToUpdate = vinylRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    if (!vinylRequest.getArtist().equals("")) {
      vinylToUpdate.setArtist(vinylRequest.getArtist());
    }
    if (!vinylRequest.getTitle().equals("")) {
      vinylToUpdate.setTitle(vinylRequest.getTitle());
    }
    if (!vinylRequest.getCountry().equals("")) {
      vinylToUpdate.setCountry(vinylRequest.getCountry());
    }
    if (vinylRequest.getYear() != 0) {
      vinylToUpdate.setYear(vinylRequest.getYear());
    }
    if (!vinylRequest.getLabel().equals("")) {
      vinylToUpdate.setLabel(vinylRequest.getLabel());
    }
    if (!vinylRequest.getGenre().equals("")) {
      vinylToUpdate.setGenre(vinylRequest.getGenre());
    }
    if (!vinylRequest.getImage().equals("")) {
      vinylToUpdate.setImage(vinylRequest.getImage());
    }
    if (vinylRequest.getPrice() != 0) {
      vinylToUpdate.setPrice(vinylRequest.getPrice());
    }

    vinylRepository.save(vinylToUpdate);
    return new ResponseEntity<Boolean>(true,HttpStatus.OK);
  }

  public void deleteVinylByID(long id) {
    vinylRepository.deleteById(id);
  }

}
