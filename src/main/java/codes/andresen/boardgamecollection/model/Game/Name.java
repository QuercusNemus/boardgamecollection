package codes.andresen.boardgamecollection.model.Game;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "name")
@XmlAccessorType(XmlAccessType.FIELD)
public class Name {
    @XmlAttribute(name = "primary")
    private boolean primary;
    @XmlValue()
    private String name;
}
