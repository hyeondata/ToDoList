package db;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@ComponentScan(basePackages = { "db" })
@Configuration
@Import({DBConfig.class})
public class ApplicationConfig {

}