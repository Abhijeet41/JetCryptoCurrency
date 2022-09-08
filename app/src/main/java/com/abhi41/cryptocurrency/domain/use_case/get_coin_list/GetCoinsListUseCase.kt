package com.abhi41.cryptocurrency.domain.use_case.get_coin_list

import com.abhi41.cryptocurrency.common.Resource
import com.abhi41.cryptocurrency.domain.model.Coin
import com.abhi41.cryptocurrency.domain.model.toCoin
import com.abhi41.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class GetCoinsListUseCase @Inject constructor(
    private val repository: CoinRepository,
) {
    //use case should have only 1 public function
    operator fun invoke(): Flow<Resource<List<Coin>>> {
        return flow {

            try {
                emit(Resource.Loading())
                val coins = repository.getCoins()
                emit(Resource.Success(data = coins.map { it.toCoin() }))
            }catch (e:HttpException){
                emit(Resource.Error(e.localizedMessage ?: "An Unexpected error"))
            }catch (e: IOException){
                emit(Resource.Error("Coulnd't reach server. Check your InternetConnect!"))
            }

        }
    }
}