package dat3.amager_records_backend.api;

import dat3.amager_records_backend.dto.VinylRequest;
import dat3.amager_records_backend.dto.VinylResponse;
import dat3.amager_records_backend.service.VinylService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/vinyl/")
public class VinylController {

  VinylService vinylService;

  public VinylController(VinylService vinylService) {
    this.vinylService = vinylService;
  }

  @GetMapping
  List<VinylResponse> vinylList() {
    return vinylService.getVinyls();
  }

  @GetMapping("{id}")
  VinylResponse vinylResponse(@PathVariable long id) {
    return vinylService.getVinylByID(id);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  VinylResponse addVinyl(@RequestBody VinylRequest vinylRequest) {
    return vinylService.addVinyl(vinylRequest);
  }

  @PutMapping("{id}")
  ResponseEntity<Boolean> editVinyl(@RequestBody VinylRequest vinylRequest, @PathVariable long id) {
    return vinylService.editVinyl(id, vinylRequest);
  }

  @DeleteMapping("{id}")
  void deleteVinylByID(@PathVariable long id) {
    vinylService.deleteVinylByID(id);
  }
}
