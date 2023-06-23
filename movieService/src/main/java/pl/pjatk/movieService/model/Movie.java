package pl.pjatk.movieService.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "movie")
@Schema(description = "Movie details")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Movie ID")
    @Min(0)
    @Max(400)
    private long id;
    @Column(name = "name")
    @Schema(description = "Movie name")
    @Size(min = 1, max = 100)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "movie_category")
    @Schema(description = "Movie category")
    MovieCategory movieCategory;

    @Column(name = "isAvailable", nullable = false)
    @Schema(description = "movie availablity")
    private boolean isAvailable;

    public Movie() {}
    public Movie(int id, String name, MovieCategory movieCategory, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.movieCategory = movieCategory;
        this.isAvailable = isAvailable;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public long getId() {
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

    public MovieCategory getMovieCategory() {
        return movieCategory;
    }

    public void setMovieCategory(MovieCategory movieCategory) {
        this.movieCategory = movieCategory;
    }
}
