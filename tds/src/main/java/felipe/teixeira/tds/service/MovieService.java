package felipe.teixeira.tds.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Service;

import felipe.teixeira.tds.domain.Movie;
import felipe.teixeira.tds.persistence.MovieRepository;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    
    public Movie newMovie(Movie newMovie) {

        movieRepository.save(null, newMovie);

        return newMovie;
    }

    public void deleteMovie(UUID id) {

        Movie m = this.find(id);
        if(m==null)
            throw new IllegalArgumentException("Filme nao encontrado!");
        
            movieRepository.delete(id);

    }   

    public void updateMovie(UUID id, Movie movie) {

        if(this.find(id)==null)
            throw new IllegalArgumentException("Filme nao encontrado!");

        movieRepository.save(id, movie);
    } 

    public Movie find(UUID id) {
        return movieRepository.findById(id);
    }

    public ArrayList<Movie> listMovies() {
        
        return movieRepository.findAll();
    }


}
