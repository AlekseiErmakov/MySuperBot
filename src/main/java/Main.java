import application.config.TelegramConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.telegram.telegrambots.ApiContextInitializer;
@ComponentScan
public class Main {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        ApplicationContext context = new AnnotationConfigApplicationContext(TelegramConfiguration.class);
    }
}
