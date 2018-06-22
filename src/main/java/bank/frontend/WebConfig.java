package bank.frontend;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan //ő is scannelje be az összes java controllert
@EnableWebMvc //indítsa el a sping mvc-t
public class WebConfig {

}
