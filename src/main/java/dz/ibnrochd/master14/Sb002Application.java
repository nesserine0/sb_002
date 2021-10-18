package dz.ibnrochd.master14;

import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dz.ibnrochd.master14.dao.ConsultationRepository;
import dz.ibnrochd.master14.dao.LigneConsultationRepository;
import dz.ibnrochd.master14.dao.PatientRepository;
import dz.ibnrochd.master14.dao.TraitementRepository;
import dz.ibnrochd.master14.model.Consultation;
import dz.ibnrochd.master14.model.Patient;

@SpringBootApplication
public class Sb002Application implements CommandLineRunner {
	
	// TODO : déclarer les autres repository de la même façon que PatientRepository
	
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	ConsultationRepository consultationRepository;
	
	@Autowired
	LigneConsultationRepository ligneConsultationRepository;
	
	@Autowired
	TraitementRepository traitementRepository;

	public static void main(String[] args) {
		SpringApplication.run(Sb002Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// TODO : récupérer la liste de tous les patients puis afficher leurs noms
		
		/*System.out.println("\n\nList des patients : \n");
		 patientRepository.findAll().forEach(pat->System.out.print(pat.getNom()+"\n"));
		
		// TODO : rechercher les patients ayant le nom "Yahi" puis afficher leurs prénoms
		 System.out.println("\nList des patients ayant le nom 'Yahi' : ");
		 patientRepository.findByNom("Yahi").forEach(pat->System.out.println(pat.getNom()+" "+pat.getPrenom()+"\n"));
			
		
		// TODO : créer un nouveau patient (valeurs au choix)  PUIS enregistrer-le
         patientRepository.save(new Patient("Touadi", "Nesserine", "F", new Date("19/02/1996"), "12345678","alger"));
       
         
     	System.out.println("\n\nList des patients apres insertion de nouveau patient: \n");
		 patientRepository.findAll().forEach(pat->System.out.print(pat.getNom()+"\n"));
		 
		// TODO : rechercher la consultation ayant id=3 
        System.out.println("\n\nConsultation ayant id = 3 : ");
		 Consultation c=consultationRepository.findById(3);
		 System.out.println(c.toStringRewrited());

		// TODO : parcourir les lignes de la consultation trouvée et afficher les noms des médicaments
		  System.out.println("\n\nLes noms des médicaments des ligne de la consultation 3 : ");
		 ligneConsultationRepository.findByConsultation(c)
		 .forEach(ligne->System.out.println( traitementRepository.findByLigneConsultations(ligne).getNom()));
		*/
	}

}
