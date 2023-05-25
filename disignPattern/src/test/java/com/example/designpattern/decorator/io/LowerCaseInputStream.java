package com.example.designpattern.decorator.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;


/*
* Decorator 역할을 하는 FilterInputStream을 상속받아서 꾸며주는 기능을 구현
* */
public class LowerCaseInputStream extends FilterInputStream {


    /*
    * Decorate 해야 할 대상의 추상 클래스를 생성자의 인자로 받아야 함
    * */
    public LowerCaseInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int read = in.read();
        return (read == -1 ? read : Character.toLowerCase((char) read));
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int result = in.read(b, off, len);
        for (int i = off; i < off + result; i++) {
            b[i] = (byte) Character.toLowerCase((char) b[i]);
        }
        return result;
    }
}
