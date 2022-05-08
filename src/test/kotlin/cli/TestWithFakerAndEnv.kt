package cli

import io.github.serpro69.kfaker.Faker

abstract class TestWithFakerAndEnv {

    protected val faker = Faker()

    protected val envKey = faker.random.randomString()

    protected val env = mutableMapOf(envKey to faker.random.randomString())
}
