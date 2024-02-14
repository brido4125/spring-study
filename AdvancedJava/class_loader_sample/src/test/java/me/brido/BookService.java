package me.brido;

import di.Inject;
import reflection.Book;

public class BookService {

  @Inject
  private BookRepository bookRepository;//이 객체가 주입되어야함

  public void rent(Book book) {
    System.out.println("BookService.rent");
  }
}
