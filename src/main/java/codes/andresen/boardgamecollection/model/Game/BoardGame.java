package codes.andresen.boardgamecollection.model.Game;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "boardgame")
@XmlAccessorType(XmlAccessType.FIELD)
public class BoardGame {
    @XmlAttribute(name = "objectid")
    private int gameId;
    @XmlElement(name = "yearpublished")
    private int yearPublished;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "minplayers")
    private int minPlayers;
    @XmlElement(name = "maxplayers")
    private int maxPlayers;
    @XmlElement(name = "playingtime")
    private int playingTime;
    @XmlElement(name = "age")
    private int age;
    @XmlElement(name = "description")
    private String description;
    @XmlElement(name = "thumbnail")
    private String thumbnail;
    @XmlElement(name = "image")
    private String image;
}
