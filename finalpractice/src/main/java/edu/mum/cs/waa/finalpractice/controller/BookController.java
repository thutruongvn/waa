package edu.mum.cs.waa.finalpractice.controller;

import edu.mum.cs.waa.finalpractice.domain.Book;
import edu.mum.cs.waa.finalpractice.domain.Category;
import edu.mum.cs.waa.finalpractice.domain.Review;
import edu.mum.cs.waa.finalpractice.repository.ReviewRepository;
import edu.mum.cs.waa.finalpractice.service.BookService;
import edu.mum.cs.waa.finalpractice.service.CategoryService;
import edu.mum.cs.waa.finalpractice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/")
    public String loadBookForm(@ModelAttribute Book book, Model model){
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "BookForm";
    }

    @PostMapping("/book_save")
    public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult,
                           Model model, RedirectAttributes redirectAttributes) {
        System.out.println(book);
        if (bindingResult.hasErrors()) {
            List<Book> books = bookService.getAllBooks();
            model.addAttribute("books", books);
            List<Category> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            return "BookForm";
        }

        String[] suppressedFields = bindingResult.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempt to bind fields that haven't been allowed in initBinder(): "
                    + StringUtils.addStringToArray(suppressedFields, ", "));
        }

        // save product here
        System.out.println(book);
        Book savedBook = bookService.saveBook(book);

//        redirectAttributes.addFlashAttribute("book", savedBook);

        return "redirect:/";
    }


    @RequestMapping(value = "/book_save_rest", method = RequestMethod.POST)
    public @ResponseBody Book saveBookRest(@Valid @RequestBody Book book, Model model) {
        System.out.println(book);
        List<Category> categories = new ArrayList<>();
        book.getCategoryList().forEach((category) -> {
            categories.add(categoryService.getCategoryById(category.getId()));
        });
        book.setCategoryList(categories);
        return bookService.saveBook(book);
    }


    @GetMapping("/book_details/{book_id}")
    public String loadBookDetails(@PathVariable("book_id") Long bookId, Model model){
        Book book = bookService.getBookById(bookId);
        model.addAttribute(book);
        Review review = new Review();
        review.setBook(book);
        model.addAttribute(review);
        return "BookDetails";
    }
    @RequestMapping(value = "/review_save", method = RequestMethod.POST)
    public String saveReview(@Valid @ModelAttribute("review") Review review, BindingResult bindingResult, Model model) {
        System.out.println(review);
        if (bindingResult.hasErrors()) {
            model.addAttribute(review.getBook());
            model.addAttribute(review);
            return "BookDetails";
        }
        // save product here
        reviewService.saveReview(review);

        return "redirect:/book_details/" + review.getBook().getId();
    }
    @RequestMapping(value = "/review_save_rest", method = RequestMethod.POST)
    public @ResponseBody Review saveReviewRest(@Valid @RequestBody Review review, Model model) {
        System.out.println(review);
        return reviewService.saveReview(review);
    }


    @GetMapping("/books/author/{author}")
    public String loadBooksByAuthor(@PathVariable("author") String author, Model model){
        List<Book> books = bookService.getBooksByAuthor(author);
        model.addAttribute("books", books);
        model.addAttribute("author", author);
        return "BooksByAuthor";
    }
}
