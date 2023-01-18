import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    id("org.springframework.boot").version("2.6.7")
    id("io.spring.dependency-management").version("1.0.11.RELEASE")
    java
}

group = "com.nchain"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.liquibase:liquibase-core")
    implementation("com.github.gwenn:sqlite-dialect:0.1.2")
    implementation("org.apache.commons:commons-io:2.11.0")
    implementation("io.springfox:springfox-swagger2:2.9.2")
    implementation("io.springfox:springfox-data-rest:2.9.2")
    implementation("org.xerial:sqlite-jdbc:3.40.0.0")
    implementation("org.apache.commons:commons-io:1.3.2")

    annotationProcessor ("org.projectlombok:lombok")
    compileOnly("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("org.junit.platform:junit-platform-launcher:1.4.0")
    testImplementation("io.rest-assured:spring-mock-mvc:5.3.0")

}

tasks.withType<Test> {
    useJUnitPlatform()
    dependsOn("cleanTest")
    dependsOn("deleteTestDb")
}

tasks.register<Delete>("deleteTestDb") {
    delete(files("${project.buildDir}/test_nchain.db"))
    delete(files("${project.projectDir}/test_nchain.db"))
}

configure<JavaPluginExtension> {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

tasks.named<BootRun>("bootRun") {
    args("--spring.profiles.active=dev")
}
