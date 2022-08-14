package com.jmlynarz.mailinglist.users

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository: PagingAndSortingRepository<User, UUID> {
}