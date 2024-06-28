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
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { ExamplePagingSource(apiService, "ExamplePagingSource") }
        ).flow
    }


    inner class ExamplePagingSource(private val apiService: MokeAPi, private val query: String?) :
        PagingSource<Int, Article>() {
        override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
//        在 Paging 库中，getRefreshKey() 方法在 PagingSource 刷新数据时发挥着关键作用。当用户执行刷新操作（ 例如， 下拉刷新，手动刷新） 时， Paging 库会调用此方法来确定从何处加载新数据。
            // 可以返回null,表示使用默认决策
            return state.anchorPosition?.let { anchorPosition ->
                val anchorPage = state.closestPageToPosition(anchorPosition) // 这里是我们返回的LoadResult.Page
                anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
            }
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
            try {
                val page = params.key ?: 1 // 第几页
                val pageSize = params.loadSize  // 每页加载条数,这个接口用不上
                val response = apiService.getArticles(page)
                return LoadResult.Page(
                    response.data,
                    if (pageSize == 1) null else pageSize - 1,
                    if (response.data.isEmpty()) null else pageSize + 1
                )
            } catch (e: Exception) {
                return LoadResult.Error(e)
            }
        }
    }
}