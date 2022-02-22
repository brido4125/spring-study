package me.brido;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.pool.TypePool;

import java.io.File;
import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class Magicain {
    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = Nike.class.getClassLoader();
        TypePool typePool = TypePool.Default.of(classLoader);
        new ByteBuddy()
                .redefine(typePool
                                .describe("me.brido.Nike")
                                .resolve(),
                        ClassFileLocator.ForClassLoader.of(classLoader))
                .method(named("out")).intercept(FixedValue.value("Newbalance"))
                .make().saveIn(new File("/Users/hongchangsub/Desktop/study/spring-study/AdvancedJava/class_loader_sample/target/classes"));

        System.out.println(new Nike().out());

    }
}
