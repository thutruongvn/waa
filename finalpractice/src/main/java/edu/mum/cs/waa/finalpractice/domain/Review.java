package edu.mum.cs.waa.finalpractice.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="T_REVIEW")
@Getter
@Setter
//@ToString
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Min(value=1)
    @Max(value=5)
    private int rate;

    @NotBlank
    private String reviewer;

    @NotEmpty
    @Size(min=4,max=255)
    private String comment;

    @ManyToOne
    @JoinColumn(name="BOOK_ID")
    private Book book;

    public Review(int rate, String reviewer, String comment) {
        this.rate = rate;
        this.reviewer = reviewer;
        this.comment = comment;
    }
}
