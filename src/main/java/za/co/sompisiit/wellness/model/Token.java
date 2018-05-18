package za.co.sompisiit.wellness.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Token {
    private String token;
    private Date expiresIn;
    private String role;

}