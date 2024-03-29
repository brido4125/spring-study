package org.example;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.*;

public class JavaAgent {
  public static void premain(String agentArgs, Instrumentation instrumentation) {
    //ByteBuddy를 통해서 바이트 코드 조작
    new AgentBuilder.Default()
            .type(ElementMatchers.any())
            .transform(((builder, typeDescription, classLoader, javaModule)
                    -> builder.method(named("pullOut")).intercept(FixedValue.value("Rabbit")))).installOn(instrumentation);
  }
}
