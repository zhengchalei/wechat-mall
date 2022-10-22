package cc.amiya.wechat.mall.service

import cc.amiya.wechat.mall.domain.Goods
import cc.amiya.wechat.mall.repository.GoodsRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Criteria.where
import org.springframework.data.relational.core.query.Query
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
@Transactional(rollbackFor = [Exception::class])
class GoodsService(
    val goodsRepository: GoodsRepository,
    val r2dbcEntityTemplate: R2dbcEntityTemplate
) {

    fun addGoods(goods: Goods): Mono<Goods> {
        return this.goodsRepository.save(goods)
    }

    fun goodsById(id: Int): Mono<Goods> {
        return this.goodsRepository.findById(id)
    }

    fun updateGoods(goods: Goods): Mono<Goods> {
        return this.goodsRepository
            .findById(goods.id)
            .doOnNext { if (it == null) throw NotFoundException() }
            .map {
                Goods(
                    id = it.id,
                    title = it.title,
                    primaryImage = it.primaryImage,
                    images = it.images,
                    tags = it.tags,
                    description = it.description,
                    goodsSpecifications = it.goodsSpecifications
                )
            }
            .flatMap { this.goodsRepository.save(it) }
    }


    fun goods(page: Int, size: Int, title: String?): Flux<Goods> {
        val titleLike = if (!title.isNullOrBlank()) {
            val like = where("title").like("%$title%")
            like
        } else {
            Criteria.empty()
        }

        return this.r2dbcEntityTemplate
            .select(Goods::class.java)
            .matching(
                Query.query(titleLike).limit(size).offset(page.times(size).minus(1).toLong())
            )
            .all()
    }
}