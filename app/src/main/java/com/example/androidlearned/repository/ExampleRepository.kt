package com.example.androidlearned.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.androidlearned.api.MokeAPi
import com.example.androidlearned.entity.Article
import kotlinx.coroutines.flow.Flow

class ExampleRepository (private val apiService: MokeAPi) {
    fun getExamplePagingData(): Flow<PagingData<Article>> {
        // pagesize 表示每页加载多少条数据，prefetchDistance表示提前加载多少条数据，如果为1，则到最后一项才加载，默认值为pageSize
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 10, enablePlaceholders = false),
            pagingSourceFactory = { ExamplePagingSource(apiService, "ExamplePagingSource") }
        ).flow
    }


    inner class ExamplePagingSource(private val apiService: MokeAPi, private val query: String?) :
        PagingSource<Int, Article>() {
        override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
//        在 Paging 库中，getRefreshKey() 方法在 PagingSource 刷新数据时发挥着关键作用。当用户执行刷新操作（ 例如， 下拉刷新，手动刷新） 时， Paging 库会调用此方法来确定从何处加载新数据。
            // 可以返回null,表示使用默认决策，什么都不处理
            return null
//            return state.anchorPosition?.let { anchorPosition ->
//                val anchorPage = state.closestPageToPosition(anchorPosition) // 这里是我们返回的LoadResult.Page
//                anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//            }
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
            try {
                val page = params.key ?: 1 // 第几页
                val pageSize = params.loadSize  // 每页加载条数,这个接口用不上
                val response = apiService.getArticles(page)
//                在构造 LoadResult.Page 时，如果无法沿相应方向加载列表，则给 nextKey 或 prevKey 传递 null。
//                例如，在我们的示例中，我们会考虑这样一种情况：如果网络响应成功但列表为空，我们就没有剩余的数据可加载了；因此 nextKey 可以为 null。
                return LoadResult.Page(
                    response.data.datas,
                    null, // 这里只能向下加载，不能先上加载数据，因此 prevKey 为 null
                    if(response.data.datas.isNotEmpty()) response.data.curPage else null // 如果没数据了，返回null
                )
            } catch (e: Exception) {
                return LoadResult.Error(e)
            }
        }
    }
}