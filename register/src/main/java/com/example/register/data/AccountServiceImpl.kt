/*
Copyright 2022 Google LLC

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.example.register.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class AccountServiceImpl @Inject constructor() : AccountService {
    override fun hasUser(): Boolean {
        return Firebase.auth.currentUser != null
    }

    override fun isAnonymousUser(): Boolean {
        return Firebase.auth.currentUser?.isAnonymous ?: true
    }

    override fun getUserId(): String {
        return Firebase.auth.currentUser?.uid.orEmpty()
    }


    override fun signIn(email: String, password: String, onResult: (Throwable?) -> Unit) {

        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
//                println(Firebase.auth.currentUser?.email)
//                println(Firebase.auth.currentUser?.displayName)
//                println(Firebase.auth.currentUser?.uid)
                onResult(it.exception)
            }
    }

    override fun sendRecoveryEmail(email: String, onResult: (Throwable?) -> Unit) {
        Firebase.auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { onResult(it.exception) }
    }

    override fun createAnonymousAccount(onResult: (Throwable?) -> Unit) {
        Firebase.auth.signInAnonymously()
            .addOnCompleteListener { onResult(it.exception) }
    }

    override fun createAccount(
        name: String,
        email: String,
        password: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                updateProfile(name)
                onResult(it.exception)
            }
    }

    override fun updateProfile(name: String?, email: String?, photoUrl: String?) {
        val profileUpdates = userProfileChangeRequest {
            displayName = name
        }
        Firebase.auth.currentUser?.updateProfile(profileUpdates)
    }

    override fun deleteAccount(onResult: (Throwable?) -> Unit) {
        Firebase.auth.currentUser!!.delete()
            .addOnCompleteListener { onResult(it.exception) }
    }

    override fun signOut() {
        Firebase.auth.signOut()
    }
}
