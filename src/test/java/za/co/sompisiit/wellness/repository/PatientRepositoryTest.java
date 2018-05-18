package za.co.sompisiit.wellness.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.sompisiit.wellness.model.Patient;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PatientRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    PatientRepository patientRepository;


    @Test
    public void shouldRetrieveHighRiskPatientsWhenBodyMassIndexIsGreaterThan30() {
        Patient patient = getPatient();
        testEntityManager.persistAndFlush(patient);
        List<Patient> highRiskPatients = patientRepository.findHighRiskPatients();
        assertThat(highRiskPatients.get(0).getBodyMassIndex()).isGreaterThan(30);
        assertThat(highRiskPatients).isNotNull();
        assertThat(highRiskPatients.get(0).getName()).isEqualToIgnoringCase("peter");
    }

    @Test
    public void shouldRetrieveHighRiskPatientsWhenSBPressureAndDBPressureAreHigh() throws JsonProcessingException {
        Patient patient = getPatient();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(patient));
        patient.setHeight(2.3);
        patient.setSystolicBloodPressure(141);
        patient.setDiastolicBloodPressure(91);

        testEntityManager.persistAndFlush(patient);

        List<Patient> highRiskPatients = patientRepository.findHighRiskPatients();
        assertThat(highRiskPatients.get(0).getBodyMassIndex()).isLessThanOrEqualTo(30);
        assertThat(highRiskPatients.get(0).getSystolicBloodPressure()).isGreaterThan(140);
        assertThat(highRiskPatients.get(0).getDiastolicBloodPressure()).isGreaterThan(90);
        assertThat(highRiskPatients).isNotNull();
        assertThat(highRiskPatients.get(0).getName()).isEqualToIgnoringCase(patient.getName());

    }


    private Patient getPatient() {
        return Patient.builder()
                    .name("Peter")
                    .surname("Frampton")
                    .systolicBloodPressure(139)
                    .diastolicBloodPressure(89)
                    .email("peterF@gmail.com")
                    .height(1.6)
                    .weight(90)
                    .build();
    }
}
