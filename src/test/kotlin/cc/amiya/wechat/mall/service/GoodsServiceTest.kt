package cc.amiya.wechat.mall.service

import cc.amiya.wechat.mall.domain.Goods
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class GoodsServiceTest() {

    @Autowired
    lateinit var goodsService: GoodsService

    @Test
    fun addGoods() {
        val goods = Goods(
            id = 0,
            title = "纯色纯棉休闲圆领短袖T恤纯白亲肤厚柔软细腻面料纯白短袖套头T恤",
            primaryImage = "https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-08b.png",
            images = listOf(
                "https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-09a.png",
                "https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-09b.png",
            ),
            description = listOf(
                "https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-09a.png",
                "https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-09b.png",
            ),
            tags = listOf(),
            goodsSpecifications = listOf(),
        )


        val block = goodsService.addGoods(goods)
            .filter { it != null }
            .flatMap { goodsService.goodsById(it.id) }
            .block()
        println(block)
    }

}