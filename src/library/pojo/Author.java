package library.pojo;

public class Author {
    private int author_id;
    private String author_full_name;
    private String author_biography;

    public Author(int author_id, String author_full_name, String author_biography) {
        this.author_id = author_id;
        this.author_full_name = author_full_name;
        this.author_biography = author_biography;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public String getAuthor_full_name() {
        return author_full_name;
    }

    public String getAuthor_biography() {
        return author_biography;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public void setAuthor_full_name(String author_full_name) {
        this.author_full_name = author_full_name;
    }

    public void setAuthor_biography(String author_biography) {
        this.author_biography = author_biography;
    }
}