package library;

import library.data.*;
import library.pojo.Author;
import library.pojo.Book;
import library.pojo.Publisher;

import java.sql.*;


public class Main {

    public static void main(String[] args) {

        AuthorDataAccess x = new AuthorDataAccess();
        PublisherDataAccess y = new PublisherDataAccess();
        BookDataAccess z = new BookDataAccess();

        Author a1 = new Author(1, "Muhammad", "Al-Halaby");
        Publisher p1 = new Publisher(1, "Osama Maani");
        Book b1 = new Book(1, "345234234", "Graduation Projects 101", 1000, "Tech", "Arabic", 455, 1, 1);

        x.insert(a1);
        y.insert(p1);
        z.insert(b1);
    }
}
