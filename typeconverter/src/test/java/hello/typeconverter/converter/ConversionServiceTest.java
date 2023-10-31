package hello.typeconverter.converter;

import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

public class ConversionServiceTest {
    @Test
    void conversionServiceTest() {
        //register converter -> 등록하는 컨버터의 정확한 타입을 알고 있어야함
        DefaultConversionService conversionService = new DefaultConversionService();

        // DefaultConversionService는 컨버터 사용에 초점이 맞춰진 ConversionService, 컨버터 등록에 초점이 맞춰진 ConverterRegistry
        // 각각 두가지 서로 다른 인터페이스를 구현하고 있다. -> 관심사를 명확하게 분리할 수 있음

        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());

        Integer result = conversionService.convert("10", Integer.class);
        System.out.println("result = " + result);
    }
}
