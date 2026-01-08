plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id("org.jetbrains.kotlin.plugin.compose") version "2.3.0"
}

android {
	namespace = "de.lapid.lapidnfckitsample"
	compileSdk = 36

	defaultConfig {
		applicationId = "de.lapid.lapidnfckitsample"
		minSdk = 28
		targetSdk = 36
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables {
			useSupportLibrary = true
		}
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_21
		targetCompatibility = JavaVersion.VERSION_21
	}
	buildFeatures {
		compose = true
		viewBinding = false
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

dependencies {
	//noinspection UseTomlInstead
	implementation ("de.lapid.nfckit:nfckit:1.0.12") // Production
	//implementation ("de.lapid.nfckit:nfckit-test:1.3520") // Test
	implementation("com.google.android.material:material:1.13.0")
	implementation("androidx.core:core-ktx:1.17.0")
	implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.10.0")
	implementation("androidx.activity:activity-compose:1.12.2")
	implementation(platform("androidx.compose:compose-bom:2025.12.01"))
	implementation("androidx.compose.ui:ui")
	implementation("androidx.compose.ui:ui-graphics")
	implementation("androidx.compose.ui:ui-tooling-preview")
	implementation ("androidx.compose.material3:material3")
	debugImplementation("androidx.compose.ui:ui-tooling")
	debugImplementation("androidx.compose.ui:ui-test-manifest")

	// Compose
	implementation ("com.airbnb.android:lottie-compose:6.7.1")
	implementation ("androidx.navigation:navigation-compose:2.9.6")
	implementation ("androidx.camera:camera-camera2:1.5.2")
	implementation ("androidx.camera:camera-view:1.5.2")
	implementation ("androidx.camera:camera-lifecycle:1.5.2")
	implementation ("com.google.accompanist:accompanist-permissions:0.37.3")

	//Retrofit
	implementation ("com.squareup.retrofit2:retrofit:3.0.0")
	implementation ("com.squareup.retrofit2:converter-gson:3.0.0")
	implementation ("com.squareup.okhttp3:okhttp:5.3.2")

	// ML Kit dependencies
	implementation ("com.google.android.gms:play-services-mlkit-text-recognition:19.0.1")
}