package pl.pjwstk.RentalService.Model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Movie {

    private long id;

    private String name;
    @NotNull
    @NotBlank
    MovieCategory movieCategory;

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
