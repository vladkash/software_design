package cli

import cli.environments.MutableEnv
import io.github.serpro69.kfaker.Faker

abstract class TestWithFakerAndEnv {

    protected val faker = Faker()

    protected val envKey = faker.random.randomString()

    protected val env = MutableEnv(
        location = System.getProperty("user.dir"),
        vars = mutableMapOf(envKey to faker.random.randomString())
    )
}
