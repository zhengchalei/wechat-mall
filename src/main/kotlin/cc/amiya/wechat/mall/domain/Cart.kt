package cc.amiya.wechat.mall.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "cart")
data class Cart(

    @Id
    val id: Int = 0,

    // 商品id
    var goodsId: List<Int>

)
