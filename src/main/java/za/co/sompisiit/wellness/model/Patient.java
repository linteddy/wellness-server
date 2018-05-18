package za.co.sompisiit.wellness.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.math3.util.Precision;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends AuditedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private double height;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false, name = "systolic_blood_pressure")
    private double systolicBloodPressure;

    @Column(nullable = false, name = "diastolic_blood_pressure")
    private double diastolicBloodPressure;

    private double bodyMassIndex;

    @PrePersist
    @PreUpdate
    private void calculateBodyMassIndex() {
        bodyMassIndex = weight / (height * height);
        bodyMassIndex = Precision.round(bodyMassIndex,2);
    }
}
