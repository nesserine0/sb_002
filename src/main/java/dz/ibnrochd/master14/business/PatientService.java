package dz.ibnrochd.master14.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import dz.ibnrochd.master14.dao.PatientRepository;
import dz.ibnrochd.master14.model.Patient;

@Service
public class PatientService implements IPatientService{
	
	@Autowired
	PatientRepository patientRepository;

	@Override
	public List<Patient> listeDesPatients() {
		// TODO Auto-generated method stub
		return patientRepository.findAll();
	}

	@Override
	public void creerPatient(Patient patient) {
		// TODO Auto-generated method stub
		patientRepository.save(patient);
	}

	@Override
	public void deletePatient(Patient patient) {
		// TODO Auto-generated method stub
		patientRepository.delete(patient);
		
	}

	
	@Override
	public void updatePatient(Long id, Patient patient) {
		// TODO Auto-generated method stub
		
		 Patient p;
		 
			Optional <Patient> optionalEntity = patientRepository.findById(id);
			if (optionalEntity.isPresent()) {
				
			    p = optionalEntity.get();
			    p.setAdresse(patient.getAdresse());
				p.setConsultations(patient.getConsultations());
				p.setDateNaissance(patient.getDateNaissance());
				p.setNom(patient.getNom());
				p.setNumeroTelephone(patient.getNumeroTelephone());
				p.setPrenom(patient.getPrenom());
				p.setRendezVous(patient.getRendezVous());
				p.setSexe(patient.getSexe());
				patientRepository.save(p);
			}
			
			else System.out.print("patient est null");	
			
		
	}

	@Override
	public void deletePatientByid(Long id) {
		// TODO Auto-generated method stub
		
	if (id!=null) {
		 Patient patient;
		System.out.print("the passed id is "+id);
		
		Optional <Patient> optionalEntity = patientRepository.findById(id);
		if (optionalEntity.isPresent()) {
			System.out.print("patient is present" );
		    patient = optionalEntity.get();
		    
			System.out.print("patient id is "+patient.getId() );
			
		    patientRepository.delete(patient);	
		}
		
		else System.out.print("No patient is found with this id ");	
	}
	else System.out.print("the passed id is null ");	
		
	
		
	}

}
