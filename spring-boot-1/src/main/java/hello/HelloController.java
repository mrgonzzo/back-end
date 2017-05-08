package hello;

import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    };
    @RequestMapping("/a")
    public String a(){
    	return "segundo metodo!";
    };
    @RequestMapping("/b")
    public  Book b(){
    	return new Book();
    };

}
