plugins {
  id("java-library")
  alias(libs.plugins.jetbrainsKotlinJvm)
  alias(libs.plugins.googleKsp)
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
  implementation(libs.retrofit2)
  implementation(libs.moshi)
  ksp(libs.moshi.codegen)
  testImplementation(libs.junit)
}