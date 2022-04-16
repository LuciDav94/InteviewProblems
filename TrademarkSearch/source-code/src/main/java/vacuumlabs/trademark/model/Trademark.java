package vacuumlabs.trademark.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TRADEMARK",
        indexes = {
                @Index(name = "trademark_word_mark_specification", columnList = "word_mark_specification")
        })
public class Trademark {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @Column(name = "word_mark_specification")
    private String wordMarkSpecification;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @Column(name = "mark_current_status_date")
    private Date markCurrentStatusDate;

    @Column(name = "application_number")
    private String applicationNumber;

    public Trademark() {
    }

    public Trademark(String wordMarkSpecification, Date registrationDate, Date expiryDate, Date markCurrentStatusDate, String applicationNumber) {
        this.wordMarkSpecification = wordMarkSpecification;
        this.registrationDate = registrationDate;
        this.expiryDate = expiryDate;
        this.markCurrentStatusDate = markCurrentStatusDate;
        this.applicationNumber = applicationNumber;
    }

    public Long getId() {
        return id;
    }

    public String getWordMarkSpecification() {
        return wordMarkSpecification;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public Date getMarkCurrentStatusDate() {
        return markCurrentStatusDate;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }
}
