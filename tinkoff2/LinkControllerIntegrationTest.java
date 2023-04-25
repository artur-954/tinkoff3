import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class LinkControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webClient;

    @Test
    public void testGetAllLinks() {
        webClient.get()
                .uri("http://localhost:" + port + "/api/links")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Link.class).hasSize(2);
    }
}