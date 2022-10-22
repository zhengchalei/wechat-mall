package cc.amiya.wechat.mall.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "goods_specification_detail")
data class GoodsSpecificationDetail(

    @Id
    val id: Int = 0,

    // 名称
    var name: String,

    // 库存数量
    var stock: Int,

    // 对饮的商品规格
    var goodsSpecificationId: Int
)
