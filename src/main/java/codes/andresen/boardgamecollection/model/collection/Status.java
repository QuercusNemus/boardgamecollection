package codes.andresen.boardgamecollection.model.collection;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "status")
@XmlAccessorType(XmlAccessType.FIELD)
public class Status {
    @XmlAttribute(name = "own")
    private int own;

    public boolean isOwned() {
        return this.own == 1;
    }
}
