package fr.fms.apitrainings.services;

import fr.fms.apitrainings.dao.ImageRepository;
import fr.fms.apitrainings.dao.TrainingRepository;
import fr.fms.apitrainings.entities.Image;
import fr.fms.apitrainings.entities.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    TrainingRepository trainingRepository;

    private final String FOLDER_PATH = "C:\\Users\\QuodbachG\\Documents\\user\\home\\trainings\\images";

    public byte[] downloadImage(Long trainingId) throws Exception {
        Optional<Training> trainingOptional = trainingRepository.findById(trainingId);
        if (!trainingOptional.isPresent()) {
            throw new Exception("Training not found");
        }

        Training training = trainingOptional.get();
        Image image = training.getImage();
        if (image == null){
            throw new Exception("No image associated with this training");
        }
        Path imagePath = Paths.get(image.getFilePath());
        return Files.readAllBytes(imagePath);
    }

    public Image uploadImage(MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH + File.separator + file.getOriginalFilename();
        Files.createDirectories(Paths.get(FOLDER_PATH)); // Assure que le dossier existe
        file.transferTo(new File(filePath));

        Image image = Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath)
                .build();

        return imageRepository.save(image);
    }

    public Training associateImageToTraining(Long trainingId, MultipartFile file) throws IOException {
        Image image = uploadImage(file);
        Optional<Training> optionalTraining = trainingRepository.findById(trainingId);
        if (optionalTraining.isPresent()) {
            Training training = optionalTraining.get();
            training.setImage(image);
            return trainingRepository.save(training);
        } else {
            throw new IllegalArgumentException("Training not found");
        }
    }

}
