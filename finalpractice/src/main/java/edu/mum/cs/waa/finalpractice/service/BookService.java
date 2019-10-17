package edu.mum.cs.waa.finalpractice.service;

import edu.mum.cs.waa.finalpractice.domain.Book;

import java.util.List;

public interface BookService {
    public Book saveBook(Book book);

    public List<Book> getAllBooks();

    public Book getBookById(Long id);

    public  List<Book> getBooksByAuthor(String authorName);
}
