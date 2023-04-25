import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

public class HttpClientImpl implements HttpClientInterface {
    private final HttpClient httpClient;
    private final URI baseUri;

    public HttpClientImpl(String baseUri) {
        this.httpClient = HttpClientBuilder.create().build();
        this.baseUri = URI.create(baseUri);
    }

    @Override
    public String get(String path) throws IOException {
        HttpGet httpGet = new HttpGet(baseUri.resolve(path));
        return executeRequest(httpGet);
    }

    @Override
    public String post(String path, String body) throws IOException {
        HttpPost httpPost = new HttpPost(baseUri.resolve(path));
        httpPost.setEntity(new StringEntity(body, ContentType.APPLICATION_JSON));
        return executeRequest(httpPost);
    }

    private String executeRequest(HttpGet httpGet) throws IOException {
        return EntityUtils.toString(httpClient.execute(httpGet).getEntity());
    }

    private String executeRequest(HttpPost httpPost) throws IOException {
        return EntityUtils.toString(httpClient.execute(httpPost).getEntity());
    }
}