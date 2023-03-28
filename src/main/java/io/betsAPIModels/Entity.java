
// Away.java

package io.betsAPIModels;

import com.fasterxml.jackson.annotation.*;

public class Entity {
    private String id;
    private String name;
    private String imageID;
    private String cc;

    @JsonProperty("id")
    public String getID() { return id; }
    @JsonProperty("id")
    public void setID(String value) { this.id = value; }

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("image_id")
    public String getImageID() { return imageID; }
    @JsonProperty("image_id")
    public void setImageID(String value) { this.imageID = value; }

    @JsonProperty("cc")
    public String getCc() { return cc; }
    @JsonProperty("cc")
    public void setCc(String value) { this.cc = value; }
}
