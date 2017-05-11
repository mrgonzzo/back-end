package movies.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Director {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id_director;
	private int age;
	private String name;
	
	
	/*public Director(int id,String name, int age) {
		this.id=id;
		this.name = name;
		this.age = age;
	}*/
	
	public int getId() {
		return id_director;
	}

	public void setId(int id) {
		this.id_director = id;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
