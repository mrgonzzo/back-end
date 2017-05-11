package movies.repositories;

import java.util.List;

import org.springframework.data.repository.*;

import movies.model.Film;

public interface MovieRepository extends CrudRepository<Film, Integer>{
	List<Film> findByCategory(String category);

}
