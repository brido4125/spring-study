package com.example.designpattern.decorator.io;

import org.junit.jupiter.api.Test;

import java.io.*;

public class InputTest {
    @Test
    void test() throws IOException {
        int c;
        try {
            InputStream in =
                    new LowerCaseInputStream(
                            new BufferedInputStream(
                                    new FileInputStream("src/test/java/com/example/designpattern/decorator/io/test.txt")));
            while ((c = in.read()) >= 0) {
                System.out.print((char) c);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void decoratorOrderTest() {
        int c;
        try {
            InputStream in =
                    new BufferedInputStream(
                            new LowerCaseInputStream(
                                    new FileInputStream("src/test/java/com/example/designpattern/decorator/io/test.txt")));
            while ((c = in.read()) >= 0) {
                System.out.print((char) c);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void oneDecoratorTest() {
        int c;
        try {
            InputStream in = new LowerCaseInputStream(
                    new FileInputStream("src/test/java/com/example/designpattern/decorator/io/test.txt"));
            while ((c = in.read()) >= 0) {
                System.out.print((char) c);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
