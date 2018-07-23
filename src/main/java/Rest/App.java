package Rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App implements CommandLineRunner {

    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.debug("Main(Debug)> debug level message");
        logger.info("Main(Info)> info level message");
        logger.warn("Main(Warn)> warn level message");
        logger.error("Main(Error)> error level message");
        logger.fatal("Main(Fatal)> fatal level message");
    }
}
