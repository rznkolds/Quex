package com.rznkolds.domain.usecase.account

import com.rznkolds.data.repository.user.UserRepository
import javax.inject.Inject

class SignOutUseCaseImpl @Inject constructor(
    private val repository: UserRepository
): SignOutUseCase {

    override suspend operator fun invoke() { repository.signOut() }
}