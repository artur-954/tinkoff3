@Configuration
public class ClientConfiguration {

    @Value("${github.base-url:https://api.github.com}")
    private String githubBaseUrl;

    @Value("${stackoverflow.base-url:https://api.stackexchange.com/2.3}")
    private String stackOverflowBaseUrl;

    @Bean
    public WebClient githubWebClient() {
        return WebClient.builder()
                .baseUrl(githubBaseUrl)
                .build();
    }

    @Bean
    public WebClient stackOverflowWebClient() {
        return WebClient.builder()
                .baseUrl(stackOverflowBaseUrl)
                .build();
    }
}