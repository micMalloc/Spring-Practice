package kr.heesu.practice.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class KotlinPracticeApplication {

    fun main(args: Array<String>) {
        runApplication<KotlinPracticeApplication>(*args)
    }
}