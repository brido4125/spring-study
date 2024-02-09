package me.brido;


import di.ContainerService;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class ContainerServiceTest {
  @Test
  public void getObject() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    BookRepository object = ContainerService.getObject(BookRepository.class);
  }
}
