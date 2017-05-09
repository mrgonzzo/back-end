package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import models.Director;
import models.Film;

@RestController
@RequestMapping(value = "/movies")
public class FilmController {
	List<Film> movies = new ArrayList<>();
	//Hashtable<K, V> Film movie = new Film("La mosca", "terror");

	public FilmController() {
		Film movie1 = new Film("La mosca", "terror");
		movie1.setId(movies.size());
		movie1.setDirector(new Director(1,"David Cronenberg", 63));
		movies.add(movie1);
		Film movie2 = new Film("Blade Runner", "ciencia-ficción");
		movie2.setId(movies.size());
		movie2.setDirector(new Director(2,"Ridley Scott", 65));
		movies.add(movie2);
	}

	@RequestMapping(method = RequestMethod.GET,value = "/createFilm")
	public  int createFilm(@ModelAttribute(name = "title") String title,
			              @ModelAttribute(name = "category") String category,
			              @ModelAttribute(name = "directorid") int id,
			              @ModelAttribute(name = "directorname") String directorname, 
			              @ModelAttribute(name = "directorage") int age) {
		Film movie = new Film(title, category);
		movie.setId(movies.size());
		movie.setDirector(new Director(id,directorname, age));
		movies.add(movie);
		return movie.getId();
	}

	/*@RequestMapping(value = "/getFilm")
	public Object getFilm(@ModelAttribute(name = "idx") int idx) {
		Film movie = movies.size() > idx ? movies.get(idx) : null;
		if (movie == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND);
		return movie;
	}*/

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")//mapeamos al getFilm solo con la id que en el navegador se pasa con get
	public Object getFilm(@PathVariable int id) {
		Film movie = movies.size() > id ? movies.get(id) : null;
		if (movie == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND);
		return movie;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "")
	public List<Film> getMovies() {
		return movies;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/eraseFilm/{id}")//mapeamos al eraseFilm solo con la id, esto no va por la url en la barra de direccion
	public String eraseFilm(@PathVariable int id) {
		String message ="";
	    Film movie = movies.get(id);
		if (movie != null){
		movies.remove(id);
		message = "ok";
		} else { 
			message = ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND).toString();
		}
		return message;
	}

}
