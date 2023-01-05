package library;

public class Book {
    private int id;
    private String title;
    private  String authorSurName;
    private  String authorFirstName;
    private String genre;
    private Boolean fiction;
    private String publisher;

    public Book(int id, String title, String authorSurName, String authorFirstName, String genre, Boolean fiction, String publisher) {
        this.id = id;
        this.title = title;
        this.authorSurName = authorSurName;
        this.authorFirstName = authorFirstName;
        this.genre = genre;
        this.fiction = fiction;
        this.publisher = publisher;
    }

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

    public String getAuthorSurName() {
        return authorSurName;
    }

    public void setAuthorSurName(String authorSurName) {
        this.authorSurName = authorSurName;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Boolean getFiction() {
        return fiction;
    }

    public void setFiction(Boolean fiction) {
        this.fiction = fiction;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
