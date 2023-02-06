package kr.dataportal.client.core.internal.extensions

import kr.dataportal.client.common.User
import kr.dataportal.client.core.model.source.SourceUser

/**
 * @author Heli
 * Created on 2023. 02. 06
 */
internal fun SourceUser.toUser(): User = let {
    User.of(
        id = id,
        name = name,
        username = username,
        email = email,
        phone = phone,
        website = website,
        address = address,
        company = company
    )
}
