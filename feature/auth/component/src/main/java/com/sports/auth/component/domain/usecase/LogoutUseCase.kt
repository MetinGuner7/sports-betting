package com.sports.auth.component.domain.usecase

import com.sports.auth.component.domain.repository.AuthRepository
import com.sports.common.base.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import com.sports.common.network.AppDispatchers
import com.sports.common.network.Dispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : UseCase<Unit, Result<Unit>> {

    override suspend operator fun invoke(params: Unit): Result<Unit> {
        return withContext(ioDispatcher) {
            authRepository.signOut()
        }
    }
}