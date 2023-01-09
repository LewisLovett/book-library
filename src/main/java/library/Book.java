package library;

public class Book {
    private String number;
    private String title;
    private  String author;
    private  String genre;
    private String subGenre;
    private String publisher;

    private int timesLoanedOut;



    public Book(String number, String title, String author, String genre, String subGenre, String publisher, int timesLoanedOut) {
        this.number = number;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.subGenre = subGenre;
        this.publisher = publisher;
        this.timesLoanedOut = timesLoanedOut;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSubGenre() {
        return subGenre;
    }

    public void setSubGenre(String subGenre) {
        this.subGenre = subGenre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getTimesLoanedOut() {
        return timesLoanedOut;
    }

    public void setTimesLoanedOut(int timesLoanedOut) {
        this.timesLoanedOut = timesLoanedOut;
    }

    @Override
    public String toString() {
        return (String.format("[%s %s written by %s | Genre: %s | Sub-genre: %s | Published By: %s | Amount of times loaned out: %s]", this.number, this.title, this.author, this.genre, this.subGenre, this.publisher, this.timesLoanedOut));
    }

}
