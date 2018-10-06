package codes.andresen.boardgamecollection.model.collection;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
    @XmlAttribute(name = "objectid")
    private int gameId;
    @XmlElement(name = "yearpublished")
    private int yearPublished;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "thumbnail")
    private String thumbnail;
    @XmlElement(name = "image")
    private String image;
    @XmlElement(name = "status")
    private Status status;
}
