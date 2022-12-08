package felipe.teixeira.tds;

import org.junit.jupiter.api.Test;

import felipe.teixeira.tds.domain.Movie;
import felipe.teixeira.tds.persistence.MovieRepository;
import felipe.teixeira.tds.service.MovieService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;

public class MovieServiceTest {

    MovieService movieService;
    MovieRepository movieRepository;
    ArrayList<Movie> movieRepositoryStub = new ArrayList<>();
    Movie movieDummy;
    Movie m;
    Movie n;
    Movie o;

    void preencheMovieRepositoryStub(){

        m = new Movie();
        n = new Movie();
        o = new Movie();
        m.setTitle("titulo0");
        n.setTitle("titulo1");
        o.setTitle("titulo2");

        movieRepositoryStub.add(m);
        movieRepositoryStub.add(n);
        movieRepositoryStub.add(o);

    }


    @BeforeEach
    void init(){

        movieDummy = new Movie();
        movieDummy.setTitle("teste");
        movieRepository = new MovieRepository();
        movieService = new MovieService(movieRepository);
        this.preencheMovieRepositoryStub();

    }

    @Test
    void testFilmeSalvadoCorretamente(){

        movieService.newMovie(movieDummy);
        assertEquals(movieRepository.movies.get(0).getId(), movieDummy.getId());  

    }

    @Test
    void testFindRetornaFilme() {

        movieService.newMovie(movieDummy);
        assertTrue(movieService.find(movieDummy.getId()).getTitle().equals("teste"));

    }

    @Test
    void testListMoviesRetornaArrayListCorreto(){

        movieService.newMovie(m);
        movieService.newMovie(n);
        movieService.newMovie(o);

        ArrayList<Movie> movies = movieService.listMovies();

        for (int i = 0; i < movieRepositoryStub.size(); i++) {
            assertTrue(movieRepositoryStub.get(i).getTitle().equals(movies.get(i).getTitle()));
        }

    }

    @Test
    void testDeleteDeletaFilmeCorretamente(){

        movieService.newMovie(movieDummy);
        movieService.deleteMovie(movieDummy.getId());

        assertTrue(movieRepository.movies.isEmpty());

        movieService.newMovie(m);
        movieService.newMovie(n);
        movieService.newMovie(o);

        movieService.deleteMovie(m.getId());
        movieService.deleteMovie(n.getId());
        movieService.deleteMovie(o.getId());

        assertTrue(movieRepository.movies.isEmpty());

    }

    @Test
    void testUpdateAtualizaFilmeCorretamente(){

        for(int i = 0; i < movieRepositoryStub.size(); i++){

            Movie aa = new Movie();
            aa.setTitle("titulo"+i);
            movieService.newMovie(aa);
            movieRepositoryStub.get(i).setTitle("teste"+i);
        }

        for (int j = 0; j < movieRepositoryStub.size(); j++) {
            assertFalse(movieRepository.movies.get(j).getTitle().equals(movieRepositoryStub.get(j).getTitle()));
        }

        
        UUID id0 = movieRepository.movies.get(0).getId();
        UUID id1 = movieRepository.movies.get(1).getId();
        UUID id2 = movieRepository.movies.get(2).getId();
        Movie m0 = movieService.find(id0);
        Movie m1 = movieService.find(id1);
        Movie m2 = movieService.find(id2);
        m0.setTitle("teste0");
        m1.setTitle("teste1");
        m2.setTitle("teste2");
        movieService.updateMovie(id0, movieService.find(id0));
        movieService.updateMovie(id1, movieService.find(id1));
        movieService.updateMovie(id2, movieService.find(id2));
        
        for (int j = 0; j < movieRepositoryStub.size(); j++) {
            assertEquals(movieRepository.movies.get(j).getTitle(), movieRepositoryStub.get(j).getTitle());

        }

    }
    
}
