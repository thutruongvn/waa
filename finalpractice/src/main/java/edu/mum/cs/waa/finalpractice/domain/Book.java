package edu.mum.cs.waa.finalpractice.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="T_BOOK")
@Getter
@Setter
//@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="C_ISBN")
    @NotEmpty
    private String isbn;

    @Column(name = "C_TITLE")
    @NotBlank
    @Size(min=4, max=200, message = "{Size.Book.Title.Validation}")
    private String title;

    @Column(name="C_AUTHOR")
    @NotBlank
    private String author;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "BOOK_IDs")
    @ManyToMany(cascade = {CascadeType.ALL })
    @JoinTable(name="BOOK_CATEGORY")
    private List<Category> categoryList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private List<Review> reviewList;

}
