package io.github.linteddy.wellness.service;

import io.github.linteddy.wellness.model.Patient;
import org.springframework.stereotype.Service;
import io.github.linteddy.wellness.repository.PatientRepository;

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
