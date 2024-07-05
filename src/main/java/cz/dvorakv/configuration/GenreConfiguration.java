package cz.dvorakv.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Data
@ConfigurationProperties("cz.dvorakv")
public class GenreConfiguration {

    private List<String> genres;

}
