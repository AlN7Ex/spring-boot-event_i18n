package ru.learnup.springbootevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import ru.learnup.springbootevent.event.MyEventPublisher;

import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

@SpringBootApplication
public class SpringBootEventApplication {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("lang/text");
        return messageSource;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootEventApplication.class, args);
        Locale locale = Locale.getDefault(); // or Locale.US

        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        int findMe = rand.nextInt(1000 + 1);
        System.out.println(findMe);

        MyEventPublisher publisher = context.getBean("myEventPublisher", MyEventPublisher.class);

        publisher.publishEvent(context.getMessage("greetings", null, locale));
        publisher.publishEvent(context.getMessage("task", null, locale));

        while (true) {
            int num = input.nextInt();
            if (findMe > num) {
                publisher.publishEvent(context.getMessage("greaterNumber", null, locale));
            } else if (findMe < num) {
                publisher.publishEvent(context.getMessage("lessNumber", null, locale));
            } else {
                publisher.publishEvent(context.getMessage("guessedNumber", null, locale) + " " + findMe);
                break;
            }
        }
    }
}
