package za.co.sompisiit.wellness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.sompisiit.wellness.model.Patient;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    double BMI = 30;
    double SYSTOLIC_BLOOD_PRESSURE = 140;
    double DIASTOLIC_BLOOD_PRESSURE = 90;

    default List<Patient> findHighRiskPatients() {
        return findAllByBodyMassIndexGreaterThanOrSystolicBloodPressureGreaterThanAndDiastolicBloodPressureGreaterThan(BMI, SYSTOLIC_BLOOD_PRESSURE, DIASTOLIC_BLOOD_PRESSURE);

    }

    List<Patient> findAllByBodyMassIndexGreaterThanOrSystolicBloodPressureGreaterThanAndDiastolicBloodPressureGreaterThan(double bmi, double sbp, double dbp);

}
