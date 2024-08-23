plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id("org.jetbrains.kotlin.plugin.compose") version "2.0.10"
}

android {
	namespace = "de.lapid.lapidnfckitsample"
	compileSdk = 34

	defaultConfig {
		applicationId = "de.lapid.lapidnfckitsample"
		minSdk = 27
		targetSdk = 34
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
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	kotlinOptions {
		jvmTarget = "17"
	}
	buildFeatures {
		compose = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.15"
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

dependencies {
	implementation ("de.lapid.nfckit:nfckit-test:1.1834")
	implementation("com.google.android.material:material:1.12.0")
	implementation("androidx.core:core-ktx:1.13.1")
	implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
	implementation("androidx.activity:activity-compose:1.9.1")
	implementation(platform("androidx.compose:compose-bom:2024.08.00"))
	implementation("androidx.compose.ui:ui")
	implementation("androidx.compose.ui:ui-graphics")
	implementation("androidx.compose.ui:ui-tooling-preview")
	implementation ("androidx.compose.material3:material3:1.2.1")
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.2.1")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
	androidTestImplementation(platform("androidx.compose:compose-bom:2024.08.00"))
	androidTestImplementation("androidx.compose.ui:ui-test-junit4")
	debugImplementation("androidx.compose.ui:ui-tooling")
	debugImplementation("androidx.compose.ui:ui-test-manifest")

	// Compose
	implementation ("com.airbnb.android:lottie-compose:6.5.0")
	implementation ("androidx.navigation:navigation-compose:2.7.7")
	implementation ("androidx.camera:camera-camera2:1.3.4")
	implementation ("androidx.camera:camera-view:1.3.4")
	implementation ("androidx.camera:camera-lifecycle:1.3.4")
	implementation ("com.google.accompanist:accompanist-permissions:0.34.0")

	//Retrofit
	implementation ("com.squareup.retrofit2:retrofit:2.11.0")
	implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
	implementation ("com.squareup.okhttp3:okhttp:4.12.0")

	//Joda Time
	implementation("joda-time:joda-time:2.12.7")

	// ML Kit dependencies
	implementation ("com.google.android.gms:play-services-mlkit-text-recognition:19.0.1")
}