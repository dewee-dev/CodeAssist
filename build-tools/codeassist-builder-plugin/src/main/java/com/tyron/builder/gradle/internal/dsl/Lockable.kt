package com.tyron.builder.gradle.internal.dsl

/**
 * Marks an Android Gradle Plugin DSL interface as lockable.
 *
 * Once locked, setters generated by [AgpDslDecorator] will be disabled.
 *
 * See also [org.gradle.api.provider.HasConfigurableValue] for Gradle managed types.
 */
interface Lockable {
    fun lock()
}