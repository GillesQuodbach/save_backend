package fr.fms.apitrainings.web;

import fr.fms.apitrainings.business.IBusinessImpl;
import fr.fms.apitrainings.dao.CategoryRepository;

import fr.fms.apitrainings.entities.Category;

import fr.fms.apitrainings.entities.Training;
import fr.fms.apitrainings.exception.RecordNotFoundException;
import fr.fms.apitrainings.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@CrossOrigin("*")
/*@CrossOrigin("http://localhost:4200/")*/
@RestController
// @RequestMapping("/api")
public class TrainingController {

    @Autowired
    private IBusinessImpl iBusiness;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ImageService imageService;

    @GetMapping("/trainings")
    public List<Training> allTrainings() {
        return iBusiness.getTrainings();
    }

    @PostMapping("/trainings")
    public ResponseEntity<Training> saveTraining(@RequestBody Training training) {
        Training training1 = iBusiness.saveTraining(training);
        if(Objects.isNull(training1)) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(training.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/trainings/{id}")
    public void deleteTraining(@PathVariable("id") Long id) {
        iBusiness.deleteTraining(id);
    }

    @GetMapping("/training/{id}")
    public Training getTrainingById(@PathVariable("id") Long id) {
        return iBusiness.getTrainingById(id)
                .orElseThrow(() -> new RecordNotFoundException("Id de Formation " + id + " n'éxiste pas" ));
    }

    @GetMapping("/trainings/category/{id}")
    public List<Training> getTrainingByCategory(@PathVariable("id") Long id) {
        return iBusiness.getTrainingsByCategory(id);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return iBusiness.getCategories();
    }

    @PostMapping("/trainings/{id}/image")
    public ResponseEntity<Training> uploadImage(@PathVariable("id") Long trainingId, @RequestParam("file") MultipartFile file) {
        try {
            Training training = imageService.associateImageToTraining(trainingId, file);
            return ResponseEntity.ok(training);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/trainings/{id}/image")
    public ResponseEntity<Resource> downloadImage(@PathVariable("id") Long trainingId) {
        try {
            byte[] data = imageService.downloadImage(trainingId);
            ByteArrayResource resource = new ByteArrayResource(data);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // or appropriate type
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"image.jpg\"") // or appropriate file name
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }

}
