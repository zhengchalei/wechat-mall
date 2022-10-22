package cc.amiya.wechat.mall.config

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import io.r2dbc.postgresql.codec.Json
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions

@Configuration
class ReactivePostgresConfig(
    private val objectMapper: ObjectMapper,
    private val connectionFactory: ConnectionFactory
) : AbstractR2dbcConfiguration() {
    /**
     * Return a R2DBC [ConnectionFactory]. Annotate with [Bean] in case you want to expose a
     * [ConnectionFactory] instance to the [ApplicationContext].
     *
     * @return the configured [ConnectionFactory].
     */
    override fun connectionFactory(): ConnectionFactory {
        return connectionFactory
    }

    @Bean
    override fun r2dbcCustomConversions(): R2dbcCustomConversions {
        val converters: MutableList<Converter<*, *>> = ArrayList()

        // 不能使用 lambda, 无法推断返回值类型
        converters.add(object : Converter<List<Any>, Json> {
            override fun convert(source: List<Any>): Json {
                return Json.of(objectMapper.writeValueAsString(source))
            }
        })
        converters.add(object : Converter<Json, List<Any>> {
            override fun convert(source: Json): List<Any> {
                return objectMapper.readValue(source.asString(), object : TypeReference<List<String>>() {})
            }
        })


//        converters.add(object : Converter<Map<String, Any>, Json> {
//            override fun convert(source: Map<String, Any>): Json {
//                return Json.of(objectMapper.writeValueAsString(source))
//            }
//        })
//        converters.add(object : Converter<Json, Map<String, Any>> {
//            override fun convert(source: Json): Map<String, Any> {
//                return objectMapper.readValue(source.asString(), object : TypeReference<Map<String, Any>>() {})
//            }
//        })
        return R2dbcCustomConversions(storeConversions, converters)
    }
}