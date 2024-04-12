plugins {
  id("java-library")
  alias(libs.plugins.jetbrainsKotlinJvm)
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
  implementation(project(":modules:models"))
  implementation(libs.retrofit2)
  implementation(libs.moshi)
}