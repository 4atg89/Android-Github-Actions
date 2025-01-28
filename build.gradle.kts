// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.1"
}

detekt {
    buildUponDefaultConfig = true // preconfigure defaults
    allRules = true // activate all available (even unstable) rules.
    config.setFrom("$projectDir/config/detekt/detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
    baseline = file("$projectDir/config/baseline.xml") // a way of suppressing issues before introducing detekt
    source.setFrom("$projectDir/app/src/main/java", "$projectDir/app/src/test/java")
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html.required.set(true) // observe findings in your browser with structure and code snippets
        xml.required.set(true) // checkstyle like format mainly for integrations like Jenkins
        sarif.required.set(true) // standardized SARIF format (https://sarifweb.azurewebsites.net/) to support integrations with GitHub Code Scanning
        md.required.set(true) // simple Markdown format
    }
}

//tasks.register("detekt", io.gitlab.arturbosch.detekt.Detekt::class) {
//    description = "Custom DETEKT build for all modules"
//    parallel = true
//    ignoreFailures = false
//    buildUponDefaultConfig = true
//    setSource(projectSource)
//    config.setFrom(configFile)
//    include(kotlinFiles)
//    exclude(resourceFiles, buildFiles)
//    reports {
//        html.required.set(true)
//    }
//}