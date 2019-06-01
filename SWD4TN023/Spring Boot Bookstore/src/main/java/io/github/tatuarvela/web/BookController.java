package io.github.tatuarvela.web;

import io.github.tatuarvela.domain.Book;
import io.github.tatuarvela.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookRepository repository;

    // Login
    @RequestMapping(value="/login")
    public String login() {
        return "login";
    }

    // Show booklist
    @RequestMapping(value="/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    // Show book adding form
    @RequestMapping(value = "/add")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        return "addbook";
    }

    // Save new book
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    }

    // Delete book
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        repository.delete(bookId);
        return "redirect:../booklist";
    }

    // REST service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {
        return (List<Book>) repository.findAll();
    }

    // REST service to get book by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Book findBookRest(@PathVariable("id") Long bookId) {
        return repository.findOne(bookId);
    }
}
