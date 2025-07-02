package cinema;

import cinema.configs.CinemaProperties;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);


    }

//    @Bean
    ApplicationRunner runner(ConfigurableApplicationContext ctx){
        return args -> {
            var props = ctx.getBean(CinemaProperties.class);
            System.out.println("nCols = " + props.nCols());
            System.out.println("nRows = " + props.nRows());
            var env = ctx.getEnvironment();
            System.out.println(env);
            String nCols = env.getProperty("cinema.n-cols", "n/a");
            String nRows = env.getProperty("cinema.n-rows", "n/a");
            System.out.println("env[nCols] = " + nCols);
            System.out.println("env[nRows] = " + nRows);
            
        };
    }
}
