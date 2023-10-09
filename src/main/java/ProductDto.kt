import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

data class ProductDto(
        var id: Long,
        var name: String,
        var price: Int,
        var description: String,
        var quantity: Int,
        var createAt: LocalDateTime?
)
@RestController
class ProductController(
        val productService: ProductService
) {
    @PostMapping("/product")
    fun save(@RequestBody productDto: ProductDto): ResponseEntity<String> {
        productService.save(productDto)
        return ResponseEntity.ok("success")
    }

    @GetMapping("/products")
    fun searchByName(): ResponseEntity<List<ProductDto>> {
        return ResponseEntity.ok(productService.search())
    }

    @GetMapping("/product/name")
    fun searchByName(@RequestParam("name") name: String): ResponseEntity<ProductDto> {
        //println()
        return ResponseEntity.ok(productService.searchByName(name))
    }
}