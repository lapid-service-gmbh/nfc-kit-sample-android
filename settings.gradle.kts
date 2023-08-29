pluginManagement {
	repositories {
		google()
		mavenCentral()
		gradlePluginPortal()
	}
}
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		google()
		mavenCentral()
		maven {
			url = uri("https://maven.pkg.github.com/lapid-service-gmbh/nfc-kit-android")
			credentials {
				username = "YOUR_USERNAME"
				password = "YOUR_PASSWORD"
			}
		}
	}
}

rootProject.name = "LapIDNfcKitSample"
include(":app")
 