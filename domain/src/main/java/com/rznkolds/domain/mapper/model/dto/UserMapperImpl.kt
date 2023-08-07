package com.rznkolds.domain.mapper.model.dto

import com.rznkolds.data.dto.User
import com.rznkolds.domain.mapper.model.BaseMapper
import com.rznkolds.domain.model.UserUI
import javax.inject.Inject

class UserMapperImpl @Inject constructor() : BaseMapper<User, UserUI> {

    override fun map(input: User): UserUI {
        return UserUI(
            uid = input.uid ?: "",
            name = input.name ?: "",
            description = input.description ?: "",
            profile = input.profile ?: ""
        )
    }
}