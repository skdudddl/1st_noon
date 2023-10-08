import org.springframework.stereotype.Repository

@Repository
interface ProductDocumentRepository: ElasticsearchRepository<ProductDocument, Long>, CrudRepository<ProductDocument, Long> {
    fun findByName(name: String): ProductDocument?
}