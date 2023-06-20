package com.example.composespotify.core.resource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composespotify.features.data.model.PaginatedData

class PagingResource<T: Any>(
    private val onLoad: suspend (offset: Int, limit: Int) -> PaginatedData<T>?,
): PagingSource<Int, T>() {
    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return try {
            val page = params.key ?: 1
            val res = onLoad(page, params.loadSize)

            if (res == null) {
                LoadResult.Error(Exception())
            } else {
                LoadResult.Page(
                    data = res.items,
                    nextKey = if (res.total == page) null else page + 1,
                    prevKey = null,
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}