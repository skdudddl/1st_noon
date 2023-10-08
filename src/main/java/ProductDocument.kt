import co.elastic.clients.elasticsearch._types.mapping.FieldType
import org.springframework.web.bind.annotation.Mapping
import java.text.DateFormat
import java.time.LocalDateTime

@Document(indexName = "product")
@Mapping(mappingPath = "elastic/product-mapping.json")
@Setting(settingPath = "elastic/product-setting.json")
class ProductDocument(
        @Id
        val id: Long,
        val name: String,
        val price: Int,
        val description: String,
        val quantity: Int,

        @Field(type = FieldType.Date, format = [DateFormat.date_hour_minute_second_millis, DateFormat.epoch_millis])
        val createdAt: LocalDateTime
)