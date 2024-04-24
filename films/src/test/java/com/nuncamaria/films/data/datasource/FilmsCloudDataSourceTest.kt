package com.nuncamaria.films.data.datasource

import com.nuncamaria.network.NetworkService
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FilmsCloudDataSourceTest {

    private val sut = FilmsCloudDataSource(NetworkService)

    @Test
    fun `test change password url is correct`() = runBlocking {

        val actual = sut.getFilms()

    }
}