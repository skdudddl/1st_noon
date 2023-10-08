import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
        val productDocumentRepository: ProductDocumentRepository
) {
    @Transactional
    fun save(productDto: ProductDto) {
        productDocumentRepository.save(
                ProductDocument(
                )
        )
    }

    @Transactional(readOnly = true)
    fun search(): List<ProductDto> {
        val findProducts = productDocumentRepository.findAll()
        return findProducts.map { product ->
            ProductDto(

            )
        }
    }

    @Transactional(readOnly = true)
    fun searchByName(name: String): ProductDto {
        val findProduct = productDocumentRepository.findByName(name) ?: throw RuntimeException("not found product")
        return ProductDto(

        )
    }
}