package dz.ibnrochd.master14;

import java.util.Date;

import java.util.List;
import java.text.DateFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import dz.ibnrochd.master14.dao.ConsultationRepository;
import dz.ibnrochd.master14.dao.LigneConsultationRepository;
import dz.ibnrochd.master14.dao.PatientRepository;
import dz.ibnrochd.master14.dao.TraitementRepository;
import dz.ibnrochd.master14.model.Consultation;
import dz.ibnrochd.master14.model.Patient;
import dz.ibnrochd.master14.model.Traitement;


@EnableWebMvc
@Configuration
@SpringBootApplication
public class Sb002Application implements  CommandLineRunner , WebMvcConfigurer {
	
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

		
		/*//ACTIVITY 09
		// TODO : récupérer la liste de tous les patients puis afficher leurs noms
		
		System.out.println("\n\nList des patients : \n");
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
        Long id =(long) 3;
		Consultation c=consultationRepository.findById(id);
		System.out.println(c.toStringRewrited());

		// TODO : parcourir les lignes de la consultation trouvée et afficher les noms des médicaments
		  System.out.println("\n\nLes noms des médicaments des ligne de la consultation 3 : ");
		 ligneConsultationRepository.findByConsultation(c)
		 .forEach(ligne->{
			 
			List<Traitement> traitement=traitementRepository.findByLigneConsultations(ligne);
			for(Traitement t : traitement){
				 System.out.println(t.getNom() );
			}
			
		 });
		*/
		
	}
	
	


	@Bean
	public LocaleResolver localeResolver() {
	    SessionLocaleResolver slr = new SessionLocaleResolver();
	    slr.setDefaultLocale(Locale.FRENCH);
	    return slr;
	}
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor lci = new LocaleChangeInterceptor(); 
	    lci.setParamName("lang");
	    return lci;
	}
	

	
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(localeChangeInterceptor());
	    
	}
	
	//render CSS and images (path changes so we have to fetch in the right place)
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");

    }
	
	@Bean  
	public ResourceBundleMessageSource messageSource()  {  
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();  
	 // Read messages_xxx.properties file.
	    messageSource.setBasename("messages");  
	    return messageSource;  
	}
	

}
