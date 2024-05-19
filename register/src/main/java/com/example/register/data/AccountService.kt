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

interface AccountService {
    fun hasUser(): Boolean
    fun isAnonymousUser(): Boolean
    fun getUserId(): String
    fun signIn(email: String, password: String, onResult: (Throwable?) -> Unit)
    fun sendRecoveryEmail(email: String, onResult: (Throwable?) -> Unit)
    fun createAnonymousAccount(onResult: (Throwable?) -> Unit)
    fun createAccount(name: String, email: String, password: String, onResult: (Throwable?) -> Unit)
    fun updateProfile(name: String? = null, email: String? = null, photoUrl: String? = null)
    fun deleteAccount(onResult: (Throwable?) -> Unit)
    fun signOut()
}
