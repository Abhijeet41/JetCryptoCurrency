package com.abhi41.cryptocurrency.domain.repository

import com.abhi41.cryptocurrency.data.remote.dto.CoinDetailDto
import com.abhi41.cryptocurrency.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto

}