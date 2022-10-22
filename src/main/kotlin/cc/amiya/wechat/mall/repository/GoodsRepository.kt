package cc.amiya.wechat.mall.repository

import cc.amiya.wechat.mall.domain.Goods
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface GoodsRepository: R2dbcRepository<Goods, Int> {

}