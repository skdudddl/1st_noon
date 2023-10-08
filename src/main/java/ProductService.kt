@Service
class ProductService(
        val productDocumentRepository: ProductDocumentRepository
) {
    @Transactional
    fun save(productDto: ProductDto) {
        productDocumentRepository.save(
                ProductDocument(
                        id = productDto.id
                        , name = productDto.name
                        , price = productDto.price
                        , description = productDto.description
                        , quantity = productDto.quantity
                        , createdAt = LocalDateTime.now()
                )
        )
    }

    @Transactional(readOnly = true)
    fun search(): List<ProductDto> {
        val findProducts = productDocumentRepository.findAll()
        return findProducts.map { product ->
            ProductDto(
                    product.id,
                    product.name,
                    product.price,
                    product.description,
                    product.quantity,
                    product.createdAt
            )
        }
    }

    @Transactional(readOnly = true)
    fun searchByName(name: String): ProductDto {
        val findProduct = productDocumentRepository.findByName(name) ?: throw RuntimeException("not found product")
        return ProductDto(
                id = findProduct.id,
                name = findProduct.name,
                price = findProduct.price,
                description = findProduct.description,
                quantity = findProduct.quantity,
                createAt = findProduct.createdAt
        )
    }
}