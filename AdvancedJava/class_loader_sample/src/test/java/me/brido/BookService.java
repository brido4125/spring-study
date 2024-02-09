package me.brido;

import di.Inject;

public class BookService {

  @Inject
  private BookRepository bookRepository;//이 객체가 주입되어야함
}
