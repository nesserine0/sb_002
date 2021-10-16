package dz.ibnrochd.master14.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dz.ibnrochd.master14.business.IConsultationService;
import dz.ibnrochd.master14.business.IPatientService;
import dz.ibnrochd.master14.business.ITraitementService;
import dz.ibnrochd.master14.model.Consultation;
import dz.ibnrochd.master14.model.Patient;
import dz.ibnrochd.master14.model.Traitement;

@RestController
public class MyRestController {

	@Autowired
	private IPatientService iPatientService;
	
	@Autowired
	private ITraitementService iTraitementService;
	
	@Autowired
	private IConsultationService iConsultationService;
	
	
	 @RequestMapping(value = "/api/ListTraitement")
		public ResponseEntity<Object> ListConsultations() {

	    
	     return new ResponseEntity<>(iTraitementService.listDesTraitement(),HttpStatus.OK);
				
		}
	
	   @RequestMapping(value = "/api/ListPatient")
		public ResponseEntity<Object> ListPatients() {

         List<Patient> liste = iPatientService.listeDesPatients();
         
	     return new ResponseEntity<>(liste,HttpStatus.OK);
				
		}
	   
	   
	   @RequestMapping(value = "/api/AddPatient",method = RequestMethod.POST)
		public ResponseEntity<Object> CreerPatient(@RequestBody Patient patient) {

         iPatientService.creerPatient(patient);
        
	     return new ResponseEntity<>("Ajouter avec succee",HttpStatus.CREATED);
				
		}
	   
	   @RequestMapping(value = "/api/ModifierPatient/{id}",method = RequestMethod.PUT)
		public ResponseEntity<Object> ModifierPatient(@PathVariable("id") long id ,@RequestBody Patient patient) {


        iPatientService.updatePatient(id,patient);
       
	     return new ResponseEntity<>("Modifier avec succee",HttpStatus.OK);
				
		}
	   
	   @RequestMapping(value = "/api/DeletePatient/{id}",method = RequestMethod.DELETE)
	 		public ResponseEntity<Object> DeletePatient(@PathVariable("id") long id) {

	         iPatientService.deletePatientByid(id);
	        
	 	     return new ResponseEntity<>("Supprimé avec succee",HttpStatus.OK);
	 				
	 		}
	   
	   
}
