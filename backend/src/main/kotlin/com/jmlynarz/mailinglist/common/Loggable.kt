package com.jmlynarz.mailinglist.common

import org.slf4j.LoggerFactory

abstract class Loggable {
    protected val log = LoggerFactory.getLogger(this.javaClass.name)
}