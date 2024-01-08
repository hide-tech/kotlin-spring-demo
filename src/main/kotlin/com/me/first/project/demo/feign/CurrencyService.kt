package com.me.first.project.demo.feign

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import kotlin.system.measureTimeMillis

@Service
class CurrencyService {

    private lateinit var client: CurrencyClient

    @Autowired
    fun initialize(client: CurrencyClient) {
        this.client = client
    }

    @Scheduled(initialDelay = 10L)
    fun printCurrencyInfo() {
        GlobalScope.launch {
            val job1 = async {
                println("mogilev currency started")
                retMogilev()
            }
            val job2 = async {
                println("brest started")
                retBrest()
            }
            val job3 = async {
                println("orsha started")
                retOrsha()
            }
            val time = measureTimeMillis {
                val result = StringBuilder()
                result.append("В Могилеве доллар стоит: ${job1.await()} ")
                result.append(", а в Бресте стоит: ${job2.await()} ")
                result.append(", а в Орше: ${job3.await()}")
                println(result.toString())
            }
            println("time of execution is $time")
        }
    }

    suspend fun retMogilev(): String? {
        return client.getCurrencyMogilev().get(0).USD_out
    }

    suspend fun retBrest(): String? {
        return client.getCurrencyBrest().get(0).USD_out
    }

    suspend fun retOrsha(): String? {
        return client.getCurrencyOrsha().get(0).USD_out
    }
}