package onetoone.Painting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaintingController {

    @Autowired
    PaintingRepository paintingRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/paintings")
    List<Painting> getAllPaintings() {
        return paintingRepository.findAll();
    }

    @GetMapping(path = "/paintings/{id}")
    Painting getPaintingById(@PathVariable int id) {
        return paintingRepository.findById(id);
    }

    @PostMapping(path = "/paintings")
    String createPainting(@RequestBody Painting painting) {
        if (painting == null)
            return failure;
        paintingRepository.save(painting);
        return success;
    }

    @PutMapping(path = "/paintings/{id}")
    Painting updatePainting(@PathVariable int id, @RequestBody Painting request) {
        Painting painting = paintingRepository.findById(id);
        if (painting == null)
            return null;
        paintingRepository.save(request);
        return paintingRepository.findById(id);
    }

    @DeleteMapping(path = "/paintings/{id}")
    String deletePainting(@PathVariable int id) {
        paintingRepository.deleteById(id);
        return success;
    }
}
