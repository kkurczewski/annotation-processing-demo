package org.example

import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSNode
import com.google.devtools.ksp.visitor.KSDefaultVisitor

class CustomVisitor(
    private val environment: SymbolProcessorEnvironment
) : KSDefaultVisitor<List<String>, List<String>>() {
    override fun defaultHandler(node: KSNode, data: List<String>): List<String> {
        environment.logger.info("Processing: $node")
        return emptyList()
    }
}