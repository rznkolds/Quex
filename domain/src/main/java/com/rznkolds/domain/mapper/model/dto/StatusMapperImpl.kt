package com.rznkolds.domain.mapper.model.dto

import com.rznkolds.data.dto.Status
import com.rznkolds.domain.mapper.model.BaseMapper
import com.rznkolds.domain.model.StatusUI
import javax.inject.Inject

class StatusMapperImpl @Inject constructor() : BaseMapper<Status, StatusUI> {

    override fun map(input: Status): StatusUI {
        return StatusUI(
            received = input.received ?: false,
        )
    }
}