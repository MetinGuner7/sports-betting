package com.sports.auth.component.domain.usecase

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

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : UseCase<RegisterUseCase.Params, Flow<Resource<AuthUser>>> {

    data class Params(val email: String, val password: String)

    override suspend operator fun invoke(params: Params): Flow<Resource<AuthUser>> {
        return flow {
            val result = authRepository.registerWithEmailPassword(params.email, params.password)
            emit(result.getOrThrow())
        }
            .flowOn(ioDispatcher)
            .asResource()
    }
}