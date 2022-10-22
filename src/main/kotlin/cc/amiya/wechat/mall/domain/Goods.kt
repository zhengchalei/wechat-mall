package cc.amiya.wechat.mall.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "goods")
data class Goods(
    // 商品id
    @Id
    val id: Int = 0,

    // 商品标题
    var title: String,

    // 主页图片
    var primaryImage: String,

    // 商品图片集合
    var images: List<String>,

    // 标签
    var tags: List<String>,

    // 商品描述: 目前仅支持图片
    var description: List<String>,

    // 商品规格
    var goodsSpecifications: List<GoodsSpecification>
)
