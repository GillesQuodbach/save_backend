package fr.fms.apitrainings;

import fr.fms.apitrainings.dao.*;
import fr.fms.apitrainings.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ApiTrainingsApplication implements CommandLineRunner {

	@Autowired
	private TrainingRepository trainingRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ImageRepository imageRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApiTrainingsApplication.class, args);
	}

	@Override
	public void run(String... args) throws  Exception {
		generatedData();
	}

	public void generatedData() {
		List<Training> trainingsList = new ArrayList<>();

		Image defaultImage = new Image(null,"default", "image", "C:\\Users\\QuodbachG\\Documents\\user\\home\\trainings\\images\\default.png");
		imageRepository.save(defaultImage);

		Category informatique = new Category("Informatique", trainingsList);
		Category cuisine = new Category("Cuisine", trainingsList);
		Category anglais = new Category("Langues étrangères", trainingsList);
		Category finance = new Category("Finance et comptabilité", trainingsList);

		categoryRepository.save(informatique);
		categoryRepository.save(cuisine);
		categoryRepository.save(anglais);
		categoryRepository.save(finance);

		trainingRepository.save(new Training(null, "Java", "Formation Java", 150, 1, defaultImage,informatique));
		trainingRepository.save(new Training(null, "C", "Formation C", 100, 1, defaultImage, informatique));
		trainingRepository.save(new Training(null, "Javascript", "Formation Javascript", 120, 1, defaultImage, informatique));
		trainingRepository.save(new Training(null, "Python", "Formation Python", 300, 1, defaultImage, informatique));
		trainingRepository.save(new Training(null, "NodeJS", "Formation NodeJS", 175, 1, defaultImage, informatique));

		trainingRepository.save(new Training(null, "Bases", "Les bases de la cuisine", 90, 1, defaultImage, cuisine));
		trainingRepository.save(new Training(null, "Cuisine du monde", "Les cuisines du monde", 300, 1, defaultImage, cuisine));
		trainingRepository.save(new Training(null, "Pâtisserie", "Les bases de la pâtisserie", 310, 1, defaultImage, cuisine));
		trainingRepository.save(new Training(null, "Vegan", "Apprendre la cuisine vegan", 45, 1, defaultImage, cuisine));
		trainingRepository.save(new Training(null, "Santé", " Élaboration de plats équilibrés", 155, 1, defaultImage, cuisine));

		trainingRepository.save(new Training(null, "Anglais", "Formation à l'anglais", 150, 1, defaultImage, anglais));
		trainingRepository.save(new Training(null, "Espagnol", "Formation à l'espagnol", 100, 1, defaultImage, anglais));
		trainingRepository.save(new Training(null, "Français", "Formation au français", 120, 1, defaultImage, anglais));
		trainingRepository.save(new Training(null, "Allemand", "Formation à l'allemand", 300, 1, defaultImage, anglais));
		trainingRepository.save(new Training(null, "Mandarin", "Formation au mandarin", 175, 1, defaultImage, anglais));

		trainingRepository.save(new Training(null, "Comptabilité", "Les bases de la comptabilité", 150, 1, defaultImage, finance));
		trainingRepository.save(new Training(null, "Analyse", "Formation aux analyses financières", 100, 1, defaultImage, finance));
		trainingRepository.save(new Training(null, "Gestion", "Formation au gestion budgétaire", 120, 1, defaultImage, finance));
		trainingRepository.save(new Training(null, "Fiscalité", "Fiscalité des entreprises", 300, 1, defaultImage, finance));
		trainingRepository.save(new Training(null, "Investissement", "Investissement et gestion de portefeuille", 175, 1, defaultImage, finance));

	}

}
