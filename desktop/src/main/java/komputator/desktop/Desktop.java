package komputator.desktop;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Desktop {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Desktop.class).headless(false).run();
    }
}
