package com.sports.auth.component.domain.usecase

import com.sports.auth.component.domain.repository.AuthRepository
import com.sports.common.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAuthStateUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<Unit, Flow<Boolean>> {
    override suspend operator fun invoke(params: Unit): Flow<Boolean> {
        return authRepository.observeAuthState()
    }
}