package felipe.teixeira.tds.persistence;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import felipe.teixeira.tds.domain.Movie;


@Repository
public class MovieRepository {


    public ArrayList<Movie> movies = new ArrayList<>();

    public void save(UUID id, Movie movie) {
        this.delete(id);
        movies.add(movie);
    }

    public ArrayList<Movie> findAll() {
        return movies;
    }

    public Movie findById(UUID id) {
        for (Movie m : movies) {
            if(m.getId().equals(id))
                return m;
        }
        return null;
    }

    public void delete(UUID id) {
        for (int i = 0; i < movies.size(); i++) {
            if(movies.get(i).getId().equals(id)){
                movies.remove(i);
            }
        }
    }
    
}
