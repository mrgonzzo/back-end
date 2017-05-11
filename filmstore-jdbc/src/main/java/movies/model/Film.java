package movies.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Film {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_film;
	private String title;
	private String category;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_DIRECTOR")
	private Director director;
	
 public int getId() {
		return id_film;
	}

	public void setId(int id) {
		this.id_film = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

}
