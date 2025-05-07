package com.sports.auth.component.domain.usecase

import com.sports.auth.component.data.model.LoginRequest
import com.sports.auth.component.domain.model.AuthUser
import com.sports.auth.component.domain.repository.AuthRepository
import com.sports.common.base.UseCase
import com.sports.common.network.AppDispatchers
import com.sports.common.network.Dispatcher
import com.sports.common.resource.Resource
import com.sports.common.resource.asResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : UseCase<Unit, Flow<Resource<Boolean>>>  {

    override suspend operator fun invoke(
        params: Unit
    ): Flow<Resource<Boolean>> {
        return flow {
            val data = authRepository.getCurrentUser()
            emit(data.getOrThrow())
        }
            .flowOn(ioDispatcher)
            .asResource()
    }
}
