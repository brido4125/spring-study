package hello.typeconverter.formatter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class MyNumberFormmatterTest {


  MyNumberFormmatter formatter = new MyNumberFormmatter();

  @Test
  void parse() throws ParseException {
    Number number = formatter.parse("1,000", Locale.ENGLISH);
    Assertions.assertThat(number).isEqualTo(1000L);
  }

  @Test
  void print() {
    String print = formatter.print(1000, Locale.KOREA);
    Assertions.assertThat(print).isEqualTo("1,000");
  }
}