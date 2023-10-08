import org.springframework.context.annotation.Configuration

@Configuration
@EnableElasticsearchRepositories
class ElasticsearchConfig(
        val elasticsearchProperties: ElasticsearchProperties
) : ElasticsearchConfiguration() {
    override fun clientConfiguration(): ClientConfiguration {
        return ClientConfiguration.builder()
                .connectedTo(elasticsearchProperties.getHostAndPort())
                .build()
    }
}