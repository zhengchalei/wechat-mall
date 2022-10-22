package cc.amiya.wechat.mall.resource

import cc.amiya.wechat.mall.domain.Goods
import cc.amiya.wechat.mall.service.GoodsService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Controller
class GoodsResource(
    val goodsService: GoodsService
) {

    @QueryMapping
    fun goodsById(@Argument id: Int): Mono<Goods> {
        return this.goodsService.goodsById(id)
    }

    @QueryMapping
    fun goods(@Argument page: Int, @Argument size: Int, @Argument title: String?): Flux<Goods> {
        return this.goodsService.goods(page, size, title)
    }

}