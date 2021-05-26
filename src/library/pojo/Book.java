package library.pojo;

public class Book extends  LibraryEntity{
    private int book_id;
    private String book_isbn;
    private String book_title;
    private int book_number_of_pages;
    private String book_category;
    private String book_language;
    private int publication_year;
    private int author_id;
    private int publisher_id;

    public Book(int book_id, String book_isbn, String book_title, int book_number_of_pages, String book_category, String book_language, int publication_year, int author_id, int publisher_id) {
        this.book_id = book_id;
        this.book_isbn = book_isbn;
        this.book_title = book_title;
        this.book_number_of_pages = book_number_of_pages;
        this.book_category = book_category;
        this.book_language = book_language;
        this.publication_year = publication_year;
        this.author_id = author_id;
        this.publisher_id = publisher_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_isbn() {
        return book_isbn;
    }

    public void setBook_isbn(String book_isbn) {
        this.book_isbn = book_isbn;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public int getBook_number_of_pages() {
        return book_number_of_pages;
    }

    public void setBook_number_of_pages(int book_number_of_pages) {
        this.book_number_of_pages = book_number_of_pages;
    }

    public String getBook_category() {
        return book_category;
    }

    public void setBook_category(String book_category) {
        this.book_category = book_category;
    }

    public String getBook_language() {
        return book_language;
    }

    public void setBook_language(String book_language) {
        this.book_language = book_language;
    }

    public int getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }
}
