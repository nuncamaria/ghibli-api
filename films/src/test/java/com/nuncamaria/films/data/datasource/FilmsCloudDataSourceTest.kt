package com.nuncamaria.films.data.datasource

import kotlinx.coroutines.runBlocking
import org.junit.Test

class FilmsCloudDataSourceTest {

    private val sut = FilmsCloudDataSource()

    @Test
    fun `test change password url is correct`() = runBlocking {

        val actual = sut.getFilms()

    }
}