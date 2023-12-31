package org.example

import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated

open class CustomSymbolProcessor(
    private val environment: SymbolProcessorEnvironment,
) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        environment.logger.info("Processor started")
        resolver.getAllFiles().forEach {
            it.accept(CustomVisitor(environment), mutableListOf())
        }

        return emptyList()
    }

    override fun finish() {
        generateKtFile()
        generateResource()
    }

    private fun generateKtFile() {
        val out = environment.codeGenerator.createNewFile(Dependencies(true), "com.example.generated", "KspFile")
        out.use {
            out.write("""
                    package com.example.generated
                    
                    fun foo() {
                       println("Hello world")
                    }
                """.trimIndent().toByteArray())
        }
    }

    private fun generateResource() {
        val out = environment.codeGenerator.createNewFileByPath(Dependencies(true), "json/payload", "json")
        out.use {
            out.write("""
                    {
                        "id": 123,
                        "name": "Foo"
                    }
                """.trimIndent().toByteArray())
        }
    }
}