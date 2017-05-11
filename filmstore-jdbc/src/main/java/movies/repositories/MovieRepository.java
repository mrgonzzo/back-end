package movies.repositories;

import org.springframework.data.repository.*;

import movies.model.Film;

public interface MovieRepository extends CrudRepository<Film, Integer>{

}
