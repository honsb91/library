package com.group.libraryapp.controller.book;

import com.group.libraryapp.service.book.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService = new BookService();

    @PostMapping("/book")
    public void saveBook(){
        bookService.saveBook();
    }

}
