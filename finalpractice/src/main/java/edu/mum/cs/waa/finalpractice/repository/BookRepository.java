package edu.mum.cs.waa.finalpractice.repository;

import edu.mum.cs.waa.finalpractice.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    @Query(value = "SELECT b FROM Book b WHERE b.author = :authorName")
    public List<Book> getBooksByAuthorName(String authorName);
}
