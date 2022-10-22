package cc.amiya.wechat.mall.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "home")
data class Home(

    @Id
    val id: Int = 0,

    // 首页图片集合
    var images: List<String>,

    // 首页分组
    var groups: List<String>
)