package com.alexyuzefovich.sectionizer

/**
 * Simple indicator in order to know when data load/listening should be start and when can be ended.
 *
 * @author Alexander Yuzefovich
 * */
interface DataController {
    /**
     * Section will be visible soon. It's a good place to start data load/listening.
     * */
    fun startDataRequests()

    /**
     * Section isn't visible to User anymore. All the data requests can be stopped.
     * */
    fun stopDataRequests()
}