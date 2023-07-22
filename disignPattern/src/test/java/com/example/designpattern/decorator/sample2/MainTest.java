package com.example.designpattern.decorator.sample2;

import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainTest {

    @Test
    void mainTest() throws FileNotFoundException {
        final String FILE = "testData";
        DataOutputStream dataOutputStream = new DataOutputStream(
            new BufferedOutputStream(
                new FileOutputStream(FILE)));

    }
}
