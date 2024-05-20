pluginManagement {
  repositories {
    google {
      content {
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
        includeGroupByRegex("androidx.*")
      }
    }
    mavenCentral()
    gradlePluginPortal()
  }
}
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}

rootProject.name = "HackerNewsCompose"
include(":app")
include(":modules:models")
include(":modules:api")
include(":modules:framework-mvi")
include(":modules:newsfeed:newsfeed-mvi")
include(":modules:newsfeed:newsfeed-mvvm")
include(":modules:newsfeed:newsfeed-ui")
include(":modules:repository")
