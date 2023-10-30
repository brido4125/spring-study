package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.util.StringTokenizer;

@Slf4j
public class StringToIpPortConverter implements Converter<String, IpPort> {
    @Override
    public IpPort convert(String source) {
        log.info("convert source = {}", source);
        String[] tokens = source.split(":");
        String ip = tokens[0];
        int port = Integer.parseInt(tokens[1]);
        return new IpPort(ip, port);
    }
}
