plugins {
    kotlin("jvm") version "1.8.0"
    `java-library`
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {

    group = "kr.dataportal"
    version = "1.0.0"

    apply(plugin = "kotlin")

    dependencies {
        val junitVersion: String by project
        val mockkVersion: String by project
        val assertjCoreVersion: String by project
        val striktCoreVersion: String by project
        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("reflect"))

        testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
        testImplementation("io.mockk:mockk:$mockkVersion")
        testImplementation("org.assertj:assertj-core:$assertjCoreVersion")
        testImplementation("io.strikt:strikt-core:$striktCoreVersion")
    }

    tasks {
        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "1.8"
            }
        }
        compileTestKotlin {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
