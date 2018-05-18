package za.co.sompisiit.wellness.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class AuditedEntity {

    @CreatedDate
    @Column(name = "captured_on")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

    @CreatedBy
    @Column(name = "captured_by")
    private String createdBy;
}
