package vacuumlabs.trademark.script.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class WordMarkSpecification {
    @XmlElement
    public String MarkVerbalElementText;

    public String getMarkVerbalElementText() {
        return MarkVerbalElementText;
    }
}
