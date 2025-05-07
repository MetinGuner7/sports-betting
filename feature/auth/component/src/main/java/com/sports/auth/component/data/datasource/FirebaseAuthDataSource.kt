package com.sports.auth.component.data.datasource

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthDataSource {

    override fun observeAuthState(): Flow<Boolean> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser != null)
        }
        firebaseAuth.addAuthStateListener(authStateListener)
        awaitClose { firebaseAuth.removeAuthStateListener(authStateListener) }
    }

    override fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser

    override suspend fun signInWithEmailPassword(email: String, password: String): AuthResult {
        return firebaseAuth.signInWithEmailAndPassword(email, password).await() // await() ile suspend fonksiyona çevir
    }

    override suspend fun registerWithEmailPassword(email: String, password: String): AuthResult {
        return firebaseAuth.createUserWithEmailAndPassword(email, password).await()
    }

    // override suspend fun signInAnonymously(): AuthResult {
    //    return firebaseAuth.signInAnonymously().await()
    // }

    override fun signOut() {
        firebaseAuth.signOut()
    }
}