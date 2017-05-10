package movies.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import movies.model.Director;
import movies.model.Film;
import movies.repositories.FilmRepository;
//instalar en el navegador el plugin  RESTED
@RestController
@RequestMapping(value = "/movies")
public class FilmController {
	List<Film> movies = new ArrayList<>();
	@Autowired
	FilmRepository fr;

	public FilmController() {

		Director d1 = new Director();
		d1.setName("David Cronenberg");
		d1.setAge(63);
		Director d2 = new Director();
		d2.setName("Ridley Scott");
		d2.setAge(75);

		Film movie1 = new Film();
		movie1.setTitle("La mosca");
		movie1.setCategory("terror");
		movie1.setId(movies.size());
		movie1.setDirector(d1);
		movies.add(movie1);
		Film movie2 = new Film();
		movie1.setTitle("Blade Runner");
		movie1.setCategory("ciencia-ficción");
		movie2.setId(movies.size());
		movie2.setDirector(d2);
		movies.add(movie2);
	};

	

	@RequestMapping(method = RequestMethod.GET)
	public List<Film> list() {
		List<Film> movies = fr.list();
		return movies;
	};
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public Object update(final @RequestBody Film f, @PathVariable int id) {
		Object o = fr.update(f, id);
		return o;
	}
}
/*	

	

		Film f2 = jdbcTemplate.queryForObject(SQL_SELECT_MOVIE, new Object[] { id }, moviesRowMapper);
		jdbcTemplate.update(SQL_UPDATE_DIRECTOR, f.getDirector().getName(), f.getDirector().getAge(),
				f.getDirector().getId());
		int rows = jdbcTemplate.update(SQL_UPDATE_MOVIE, f.getTitle(), f.getCategory(), id);
		return Collections.singletonMap("success", rows == 1);

	}

	@RequestMapping(method = RequestMethod.POST)
	public Object create(@RequestBody Film f) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_DIRECTOR, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, f.getDirector().getName());
				ps.setInt(2, f.getDirector().getAge());
				return ps;
			}
		};

		jdbcTemplate.update(psc, keyHolder);

		PreparedStatementCreator psc2 = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_MOVIE, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, f.getTitle());
				ps.setString(2, f.getCategory());
				ps.setInt(3, keyHolder.getKey().intValue());
				return ps;
			}
		};

		KeyHolder keyHolder2 = new GeneratedKeyHolder();
		jdbcTemplate.update(psc2, keyHolder2);
		f.setId(keyHolder2.getKey().intValue());
		f.getDirector().setId(keyHolder.getKey().intValue());

		return f;
	};

	/*
	 * @RequestMapping(method = RequestMethod.GET, value = "/createFilm") public
	 * int createFilm(@ModelAttribute(name = "title") String title,
	 * 
	 * @ModelAttribute(name = "category") String category, @ModelAttribute(name
	 * = "directorid") int directorid,
	 * 
	 * @ModelAttribute(name = "directorname") String
	 * directorname, @ModelAttribute(name = "directorage") int age) { Film movie
	 * = new Film(); Director director=new Director(); director.setAge(age);
	 * director.setName(directorname); director.setId(directorid);
	 * movie.setId(movies.size()); movie.setCategory(category);
	 * movie.setTitle(title); movie.setDirector(director); movies.add(movie);
	 * return movie.getId(); }
	 */

	/*
	 * @RequestMapping(value = "/getFilm") public Object
	 * getFilm(@ModelAttribute(name = "idx") int idx) { Film movie =
	 * movies.size() > idx ? movies.get(idx) : null; if (movie == null) return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND);
	 * return movie; }
	 *

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Object getFilm(@PathVariable int id) {
		Film f = jdbcTemplate.queryForObject(SQL_SELECT_MOVIE, new Object[] { id }, moviesRowMapper);

		return f;
	};

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public Object delete(@PathVariable int id) {
		int rows = jdbcTemplate.update(SQL_DELETE_MOVIE, id);
		return Collections.singletonMap("success", rows == 1);
	};

	/*
	 * @RequestMapping(method = RequestMethod.GET, value = "/eraseFilm/{id}") //
	 * mapeamos // al // eraseFilm // solo // con // la // id, // esto // no //
	 * va // por // la // url // en // la // barra // de // direccion public
	 * String eraseFilm(@PathVariable int id) { String message = ""; Film movie
	 * = movies.get(id); if (movie != null) { movies.remove(id); message = "ok";
	 * } else { message =
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND).
	 * toString(); } return message; }
	

} */
