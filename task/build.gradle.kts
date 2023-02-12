import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.8"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
    kotlin("kapt") version "1.6.10"
    id("war")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11
val qeurydslVersion = "4.4.0" // <= 추가 설정

repositories {
    mavenCentral()
}

sourceSets["main"].withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
    kotlin.srcDir("$buildDir/generated/source/kapt/main")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    runtimeOnly("com.mysql:mysql-connector-j")

    implementation("org.springframework.boot:spring-boot-starter-web-services")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    compileOnly("com.fasterxml.jackson.core:jackson-core:2.9.3")
    compileOnly("com.fasterxml.jackson.core:jackson-databind:2.9.3")
    compileOnly("com.google.code.gson:gson:2.8.5")
    compileOnly("com.googlecode.json-simple:json-simple:1.1.1")
    implementation("com.jayway.jsonpath:json-path:2.5.0")

    implementation("org.apache.httpcomponents:httpclient:4.5")
    // querydsl (추가 설정)
    implementation("com.querydsl:querydsl-jpa:5.0.0")
    kapt("com.querydsl:querydsl-apt:5.0.0:jpa")
    // https://mvnrepository.com/artifact/javax.servlet/jstl
    implementation("javax.servlet:jstl:1.2")
    // https://mvnrepository.com/artifact/org.apache.tomcat.embed/tomcat-embed-jasper
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper:9.0.60")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // Spring Rest Docs
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
