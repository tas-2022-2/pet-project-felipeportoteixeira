package felipe.teixeira.tds.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import felipe.teixeira.tds.domain.Movie;
import felipe.teixeira.tds.service.MovieService;

import java.net.URI;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;



@RestController // Spring/Injeção de Dependência
@RequestMapping(
    path = "/api/movies/", 
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<?> newMovie(@RequestBody Movie movie) {

        Movie newMovie = movieService.newMovie(movie);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newMovie.getId()).toUri();

        return ResponseEntity.status(HttpStatus.CREATED).header("Location", uri.toString()).build();

    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateMovie(
        @PathVariable(value = "id") UUID id,
        @RequestBody Movie movie) {

            movieService.updateMovie(id, movie);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findMovie(@PathVariable("id") UUID id) {

        Movie movie = movieService.find(id);

        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie (@PathVariable(value = "id") UUID id) {

        try {
            movieService.deleteMovie(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping
    public ArrayList<Movie> listMovies() {
        
        return movieService.listMovies();

    }
    
}
