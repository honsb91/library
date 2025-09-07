package com.group.libraryapp.service.book;

import com.group.libraryapp.repository.book.BookMemoryRepository;
import com.group.libraryapp.repository.book.BookRepository;

public class BookService {

    private final BookRepository bookRepository = new BookMemoryRepository();

    public void saveBook(){
        bookRepository.saveBook();
    }
}
