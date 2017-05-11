package movies.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import movies.model.Film;
import movies.repositories.FilmRepository;

@RestController
@RequestMapping(value = "/movies")
public class FilmController {
	List<Film> movies = new ArrayList<>();
	@Autowired
	FilmRepository fr;
	Object o;

	@RequestMapping(method = RequestMethod.GET)
	public List<Film> list() {
		List<Film> movies = fr.list();
		return movies;
	};

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public Object update(final @RequestBody Film f, @PathVariable int id) {
		o = fr.update(f, id);
		return o;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object create(@RequestBody Film f) {
		o = fr.create(f);
		return o;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public Object delete(@PathVariable int id) {
		o = fr.delete(id);
		return o;

	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Object getFilm(@PathVariable int id) {
		Film f = fr.getMovie(id);
		return f;
	};
	@RequestMapping(method = RequestMethod.GET, value = "/cat/{category}")
	public List<Film> listByCategory(@PathVariable String category ) {
		List<Film> lc = fr.listCategory(category);
		return lc;
	}

}
