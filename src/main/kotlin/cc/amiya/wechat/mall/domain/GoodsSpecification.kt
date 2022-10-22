package cc.amiya.wechat.mall.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal

@Table(name = "goods_specification")
data class GoodsSpecification(

    @Id
    val id: Int = 0,

    // 关联商品
    var goodsId: Int,

    // 规格名称
    var name: String,

    // 规格中最低价
    var minSalePrice: BigDecimal,

    // 规格中最高价
    var maxSalePrice: BigDecimal,

)
