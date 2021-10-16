package dz.ibnrochd.master14.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import dz.ibnrochd.master14.business.IConsultationService;
import dz.ibnrochd.master14.business.IPatientService;
import dz.ibnrochd.master14.business.ITraitementService;
import dz.ibnrochd.master14.model.Consultation;
import dz.ibnrochd.master14.model.Patient;
import dz.ibnrochd.master14.model.Traitement;

@Controller
public class MyController implements ErrorController {

	private static final String Error_PATH="/error";
	
	@Autowired
	private IPatientService iPatientService;
	
	@Autowired
	private ITraitementService iTraitementService;
	
	@Autowired
	private IConsultationService iConsultationService;
	
	@Autowired
	MessageSource messageSource;
	
	
	
	 
	
	//go to pages
	
	   @RequestMapping(value = "/",method = RequestMethod.GET)
	public String index(Model model) {


		model.addAttribute("liste", iPatientService.listeDesPatients());	
		return "ListView"; 
		
	}
	

	@RequestMapping(value = { "/CreatPatient" },method = RequestMethod.GET)
	public String newPatientPage(Model model) {

		 model.addAttribute("patient", new Patient()); 
		return "CreatPatient"; 
	}
	
	
	@RequestMapping(value = { "/UpdatePatient/{id}" },method = RequestMethod.GET)
	public String UpdatePatientPage(Model model, @PathVariable Long id) {
		
		System.out.println("Patient_Id : "+id);
		 model.addAttribute("p", iPatientService.getPatient(id)); 
		return "UpdatePatient"; 
	}

	
	@RequestMapping(value = { "/ConsultationPatient/{id}" },method = RequestMethod.GET)
	public String ConsultationPage(Model model, @PathVariable Long id) {



		 model.addAttribute("p", iPatientService.getPatient(id)); 
		
		//liste des consultation d un patient
		List<Consultation>listconConsultations=iConsultationService.listDesConsultationPatient(iPatientService.getPatient(id));
		
		
		List<Traitement> traitement=new ArrayList<>();
		for (Consultation consultation : listconConsultations) {
			traitement.addAll(iTraitementService.listDesConsultationDuneConsultation(consultation));
		}
		model.addAttribute("listeConsultations",listconConsultations);
	
		return "ConsultationView"; 
	}
	
	
	
	
	
	
	//traitement 
	
	@RequestMapping(value = { "/ajouterPatient" },method = RequestMethod.POST)
	public String addPatient(Model model, @ModelAttribute ("patient") Patient p) {
		
		try {
			iPatientService.creerPatient(p);
		} catch (Exception e) {
			System.out.println(e);
			 return  "/error";
		}
		return "redirect:/";
	}
	

	@RequestMapping(value = { "/modifierPatient/{id}"},method = RequestMethod.POST)
	public String modifierPatient(@ModelAttribute ("p") Patient p,@PathVariable Long id) {
		
		try {
			iPatientService.updatePatient(id,p);
		} catch (Exception e) {
			System.out.println(e);
			 return  "/error";
		}
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	private String delete(@PathVariable Long id){



	    System.out.println("Patient_Id : "+id);
	  
	  try {
		   iPatientService.deletePatientByid(id);
		
	} catch (Exception e) {
		// TODO: handle exception
		 System.out.println(e);
		 return  "/error";
	} 
	    return "redirect:/";
	
	}
	
	
	
	
	
	

	    @RequestMapping("/error")
    public String handleError() {

	       

	        return "error";
	    }
	
	    //to parse string type sent from html into a date type 
	    @InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
	        //convert the date Note that the conversion here should always be in the same format as the string passed in, e.g. 2015-9-9 should be yyyy-MM-dd
	        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor is a custom date editor
	    }
	

	    @Override
	public String getErrorPath() {

		// TODO Auto-generated method stub
		return Error_PATH;
	}
	
	
	
}
