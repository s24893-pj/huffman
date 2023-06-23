package pl.pjatk.movieService.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.movieService.model.Movie;
import pl.pjatk.movieService.service.MovieService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/movieService")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(summary = "GetMovies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> movieList() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }
    @Operation(summary = "Get a movie by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @Parameter(name = "id", description = "Id of the movie to retrieve", required = true)
    @GetMapping(value = "/movies/{id}")
    public ResponseEntity<Movie> movieById(@PathVariable("id") long id) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);
    }
    @Operation(summary = "Set a movie Availability")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @Parameter(name = "id", description = "Id of the movie to retrieve", required = true)
    @PutMapping("/movies/{id}/available")
    public ResponseEntity<Movie> setMovieAvailability(@PathVariable Long id) {
        Movie movie = movieService.setMovieAvailability(id);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);
    }
    @Operation(summary = "Set a movie Unavailability")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @Parameter(name = "id", description = "Id of the movie to retrieve", required = true)
    @PutMapping("/movies/{id}/unavailable")
    public ResponseEntity<Movie> setMovieUnavailable(@PathVariable("id") long id) {
        Movie movie = movieService.setMovieUnavailable(id);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);
    }
    @Operation(summary = "Add movie.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @Parameter(name = "id", description = "Id of the movie to retrieve", required = true)
    @PostMapping("/movies")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        if (movie.getId() != 0) {
            return ResponseEntity.badRequest().build();
        }
        Movie addedMovie = movieService.addMovie(movie);
        if (addedMovie == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(addedMovie);
    }
    @Operation(summary = "Update movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @Parameter(name = "id", description = "Id of the movie to retrieve", required = true)
    @PutMapping("/movies/{id}")
    public ResponseEntity<Optional<Movie>> updateMovie(@PathVariable("id") int id, @RequestBody Movie movie) {
        Optional<Movie> updatedMovie = movieService.updateMovie(id, movie);
        if (updatedMovie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMovie);
    }
    @Operation(summary = "Delete movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @Parameter(name = "id", description = "Id of the movie to retrieve", required = true)
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int id) {
        boolean isDeleted = movieService.deleteMovie(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
