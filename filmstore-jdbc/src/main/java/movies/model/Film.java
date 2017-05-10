package movies.model;

public class Film {
private int id;
private String title;
private String category;
private Director director;

/*public Film(String title,String category){
	//this.id = id;
	this.title = title;
	this.category = category;
	//this.director=director;
}*/
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
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
