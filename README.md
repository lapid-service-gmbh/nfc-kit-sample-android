# nfc-kit-sample-android

![LapID](https://media.lapid.de/logo.svg)

## About

This is an example app, which shows how to implement and handle the communication with the LapID NFC Kit.

## Installation

To be able to start the app, you need to provide your username and password in file "settings.gradle"

```kotlin
allprojects {
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
```
