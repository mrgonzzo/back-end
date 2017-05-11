package movies.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import movies.model.Film;
import movies.repositories.MovieRepository;

@RestController
@RequestMapping(value = "/movies")
public class FilmController {
	List<Film> movies = new ArrayList<>();
	@Autowired
	MovieRepository movieRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<Film> list() {
		List<Film> movies = new ArrayList<>();
		Iterable<Film> it = movieRepository.findAll();
		Iterator<Film> iterator = it.iterator();

		while (iterator.hasNext()) {
			movies.add(iterator.next());
		}
		return movies;
	};

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public Object update(final @RequestBody Film f, @PathVariable int id) {
		f.setId(id);
		movieRepository.save(f);
		return f;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object create(@RequestBody Film f) {
		f = movieRepository.save(f);
		return Collections.singletonMap("id", f);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public String delete(@PathVariable int id) {
		movieRepository.delete(id);
		return "OK";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Object getFilm(@PathVariable int id) {
		Film f = movieRepository.findOne(id);
		return f;
	};
	
	@RequestMapping(method = RequestMethod.GET, value = "/cat/{cat}")
	public List<Film> listByCategory(@PathVariable String cat) {
		List<Film> lf=movieRepository.findByCategory(cat);
		return lf;
	}
}
