package felipe.teixeira.tds.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Movie {

    private UUID id;
    private String title;
    private String description;
    private LocalDate release;

    public Movie(){
        this.setId(UUID.randomUUID());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getRelease() {
        return release;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
    }

    
}
