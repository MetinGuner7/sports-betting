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


class LoginUseCase
@Inject
constructor(
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val authRepository: AuthRepository,
) : UseCase<LoginRequest, Flow<Resource<AuthUser>>> {
    override suspend operator fun invoke(
        params: LoginRequest
    ): Flow<Resource<AuthUser>> {
        return flow {
            val data = authRepository.signInWithEmailPassword(params.email, params.password)
            emit(data.getOrThrow())
        }
            .flowOn(ioDispatcher)
            .asResource()
    }
}
