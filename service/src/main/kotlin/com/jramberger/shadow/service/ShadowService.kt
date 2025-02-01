package com.jramberger.shadow.service

import com.jramberger.shadow.service.model.Globals

abstract class ShadowService {
    abstract fun globals(): Globals
}
