package com.alexyuzefovich.sectionizer

/**
 * Simple indicator in order to know when data load/listening should be start and when can be ended.
 * It's expected that a fresh list will be fetched and passed to the adapter, when [startDataRequests]
 * is called. Since the old section may be reused in the new adapter, using static data may
 * cause the stale list is displaying.
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