package com.nology.marvelbackend;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelCharacter {

    private int id;
    private String name;
    private String description;
    private Thumbnail thumbnail;

    public MarvelCharacter(int id, String name, String description, Thumbnail thumbnail) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;

    }

    public MarvelCharacter() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "MarvelCharacter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", thumbnail=" + thumbnail +
                '}';
    }
}
