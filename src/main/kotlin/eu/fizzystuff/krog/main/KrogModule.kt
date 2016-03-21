package eu.fizzystuff.krog.main

import com.google.inject.AbstractModule
import eu.fizzystuff.krog.scenes.visibility.RaycastingVisibilityStrategy
import eu.fizzystuff.krog.scenes.visibility.VisibilityStrategy
import org.apache.commons.math3.random.MersenneTwister
import org.apache.commons.math3.random.RandomGenerator

class KrogModule : AbstractModule() {
    override fun configure() {
        bind(RandomGenerator::class.java).to(MersenneTwister::class.java)
        bind(VisibilityStrategy::class.java).to(RaycastingVisibilityStrategy::class.java)
    }
}