package ru.learnup.springbootevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import ru.learnup.springbootevent.event.MyEventPublisher;

import java.util.Locale;

@SpringBootApplication
public class SpringBootEventApplication {

    public static void main(String[] args) {
       ConfigurableApplicationContext context = SpringApplication.run(SpringBootEventApplication.class, args);
       Locale locale = Locale.getDefault();

       MyEventPublisher publisher = context.getBean("myEventPublisher", MyEventPublisher.class);
       publisher.publishEvent("some");

       ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
       messageSource.setBasenames("lang/text");

       System.out.println(messageSource.getMessage("task", null, locale));
    }

}
