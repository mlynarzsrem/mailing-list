package com.jmlynarz.mailinglist.resources

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ResourceAccessesRepository: PagingAndSortingRepository<ResourceAccess, UUID> {
}