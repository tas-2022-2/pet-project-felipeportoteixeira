package felipe.teixeira.tds;

import org.junit.jupiter.api.Test;
import felipe.teixeira.tds.domain.Movie;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class MovieTest {

    @Test
    void testIdNaoNulo(){
        
        Movie movie = new Movie();

        assertNotNull(movie.getId());

    }

    @Test
    void testIdDiferentes(){

        Movie movie = new Movie();
        movie.setTitle("titanic");
        Movie movie2= new Movie();
        movie2.setTitle("titanic");

        assertFalse(movie.equals(movie2));

    }

    @Test
    void testParametrosIguais(){

        Movie movie = new Movie();
        movie.setTitle("titanic");
        movie.setDescription("afundou");
        movie.setRelease(LocalDate.now());

        Movie movie2= new Movie();
        movie2.setTitle("titanic");
        movie2.setDescription("afundou");
        movie2.setRelease(LocalDate.now());

        assertTrue(movie.getTitle().equals(movie2.getTitle()));    
        assertTrue(movie.getDescription().equals(movie2.getDescription()));   
        assertTrue(movie.getRelease().equals(movie2.getRelease()));   

    }

    @Test
    void testTitulosDiferentes(){

        Movie movie = new Movie();
        Movie movie2= new Movie();
       
        movie.setTitle("titanic");
        movie2.setTitle("titanic2");

        assertFalse(movie.getTitle()==movie2.getTitle());

    }
    
}
