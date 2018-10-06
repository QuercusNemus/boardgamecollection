package codes.andresen.boardgamecollection.model.collection;

import lombok.Data;
import org.hibernate.validator.constraints.EAN;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class Items {
    @XmlElement(name = "item")
    private List<Item> itemList;
}
