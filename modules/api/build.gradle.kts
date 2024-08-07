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
  implementation(project(":modules:models"))
  api(libs.retrofit2)
  implementation(libs.retrofit2.converter.moshi)
  api(libs.moshi)
  api(libs.okhttp)
  implementation(libs.okhttp.logging.interceptor)
  implementation(libs.koin)
  implementation(libs.timber)
}