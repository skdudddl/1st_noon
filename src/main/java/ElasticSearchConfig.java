import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Profile;

@Configuration
public class ElasticSearchConfig {

    /*@Value("${oci.es.url}")
    private String hostname; //localhost

    @Value("${oci.es.port}")
    private Integer port; //9200

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost(hostname, port, "https")));
    }*/

    RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                    new HttpHost("localhost", 9200, "https")));
    client.close();

}
