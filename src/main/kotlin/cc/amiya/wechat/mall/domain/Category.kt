package cc.amiya.wechat.mall.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "category")
data class Category(

    @Id
    val id: Int = 0,

    // 分类名称
    var name: String,

    // 显示图片
    var image: String,

    // 上级节点id
    var parentId: Int
)
