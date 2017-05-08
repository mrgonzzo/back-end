package hello;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

	List<Book> books = new ArrayList<>();

	public HelloController() {
		Book book1 = new Book("Galatea", 205);
		book1.setAuthor(new Author("Cervantes", 63));
		Book book2 = new Book("tao", 22);
		book2.setAuthor(new Author("lao tse", 99));
		books.add(book1);
		books.add(book2);
	}

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	};

	@RequestMapping("/a")
	public String a() {
		return "segundo metodo!";
	};

	@RequestMapping("/b")
	public Book b() {

		Book book = new Book("Título1", 20);
		book.setAuthor(new Author("Umberto Eco", 102));
		return book;
	}

	@RequestMapping(value = "/getBook")
	public Book gb(@ModelAttribute(name = "idx") int idx) {
		return idx < books.size() ? books.get(idx) : null;

	}
}
