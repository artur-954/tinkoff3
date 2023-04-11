@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication {
public static void main(String[] args) {
        var ctx = SpringApplication.run(ScrapperApplication.class, args);
        ApplicationConfig config = ctx.getBean(ApplicationConfig.class);
        System.out.println(config);
        }
        }