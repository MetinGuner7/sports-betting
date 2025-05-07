package com.sports.auth.component.data.repository


import com.sports.auth.component.data.datasource.AuthDataSource
import com.sports.auth.component.domain.model.AuthUser
import com.sports.auth.component.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {

    override fun observeAuthState(): Flow<Boolean> = authDataSource.observeAuthState()

    override suspend fun getCurrentUser(): Result<Boolean> {
        return runCatching {
             val temp = authDataSource.getCurrentUser()?.uid
            temp != null
        }
    }

    override suspend fun signInWithEmailPassword(email: String, password: String): Result<AuthUser> {
        return runCatching {
            val authResult = authDataSource.signInWithEmailPassword(email, password)
            val user = authResult.user ?: throw IllegalStateException("Login sonrası Firebase user null")
            mapFirebaseUserToAuthUser(user)
        }
    }

    override suspend fun registerWithEmailPassword(email: String, password: String): Result<AuthUser> {
        return runCatching {
            val authResult = authDataSource.registerWithEmailPassword(email, password)
            val user = authResult.user ?: throw IllegalStateException("Register sonrası Firebase user null")
            mapFirebaseUserToAuthUser(user)
        }
    }

    override suspend fun signOut(): Result<Unit> {
        return runCatching { authDataSource.signOut() }
    }

    private fun mapFirebaseUserToAuthUser(firebaseUser: com.google.firebase.auth.FirebaseUser): AuthUser {
        return AuthUser(uid = firebaseUser.uid, email = firebaseUser.email)
    }
}