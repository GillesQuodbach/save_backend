package fr.fms.apitrainings.dao;

import fr.fms.apitrainings.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
 Optional<Image> findByName(String fileName);
}
