package vacuumlabs.trademark.script.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class TradeMarkXml {

    @XmlElement
    public Date RegistrationDate;
    @XmlElement
    public Date ExpiryDate;
    @XmlElement
    public String MarkFeature;
    @XmlElement
    public WordMarkSpecification WordMarkSpecification;
    @XmlElement
    public Date MarkCurrentStatusDate;
    @XmlElement
    public String ApplicationNumber;    @XmlElement
    public String ApplicationNumber;

    public Date getRegistrationDate() {
        return RegistrationDate;
    }

    public Date getExpiryDate() {
        return ExpiryDate;
    }

    public String getMarkFeature() {
        return MarkFeature;
    }

    public WordMarkSpecification getWordMarkSpecification() {
        return WordMarkSpecification;
    }

    public Date getMarkCurrentStatusDate() {
        return MarkCurrentStatusDate;
    }

    public String getApplicationNumber() {
        return ApplicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        ApplicationNumber = applicationNumber;
    }
}
