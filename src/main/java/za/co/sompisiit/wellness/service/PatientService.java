package za.co.sompisiit.wellness.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import za.co.sompisiit.wellness.model.Patient;
import za.co.sompisiit.wellness.repository.PatientRepository;

import java.util.List;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> retrieveHighRiskPatients() {
        return patientRepository.findAllByBodyMassIndexGreaterThanOrSystolicBloodPressureGreaterThanAndDiastolicBloodPressureGreaterThan(30, 140, 90);
    }

    public Patient addPatient(final Patient patient) {
        return patientRepository.save(patient);
    }
}
