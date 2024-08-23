package de.lapid.lapidnfckitsample

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.gson.GsonBuilder
import de.lapid.lapidnfckitsample.ui.components.Button
import de.lapid.lapidnfckitsample.ui.theme.LapIDNfcKitSampleTheme
import de.lapid.nfckit.LapIDNfcKitMainActivity
import de.lapid.nfckit.data.LapidNfcKitError
import de.lapid.nfckit.data.LapidNfcKitResult
import de.lapid.nfckit.data.NfcKitResult
import de.lapid.nfckit.ui.theme.DarkBlue
import de.lapid.nfckit.ui.theme.LightBlue
import de.lapid.nfckit.utilities.NfcCapability

class MainActivity : ComponentActivity() {

	private val gson = GsonBuilder().create()

	private val receiverLapIdNfcKitResult = object : BroadcastReceiver() {
		override fun onReceive(context: Context, intent: Intent) {
			val lapIdNfcKitResult = gson.fromJson(
				intent.getStringExtra(
					"LapIdNfcKitResultContent"
				), LapidNfcKitResult::class.java
			)

			when (lapIdNfcKitResult.result) {
				NfcKitResult.CHECK_COMPLETED -> {
					// The check was completed. Use the driverId and, if present, the externalDriverId to update your UI, process the information with a request etc.
					if (lapIdNfcKitResult.successful == true) {
						if (lapIdNfcKitResult.externalId != null) {
							showToast(
								"Check was successful\n" + "Driver id: ${lapIdNfcKitResult.driverId}\n" + "External id: ${lapIdNfcKitResult.externalId}"
							)
						} else {
							showToast(
								"Check was successful\n" + "Driver id: ${lapIdNfcKitResult.driverId}"
							)
						}
					} else {
						showToast("Check was not successful.")
					}
				}

				NfcKitResult.STATION_FINDER -> {
					// The user's label is incompatible with the SDK and now the user is looking for a LapID checking station.
					showToast("Open Station Finder.")
				}

				NfcKitResult.CANCELED -> {
					// The check was canceled due to an error or as a result of a user interaction. You can use this information to update your UI accordingly.
					showToast("Check was canceled.")
				}

				else -> {
					// Should never happen
					showToast("Something went wrong, this should never happen.")
				}
			}
		}
	}

	private val receiverLapIdNfcKitError = object : BroadcastReceiver() {
		override fun onReceive(context: Context, intent: Intent) {
			val lapIdNfcKitError = gson.fromJson(
				intent.getStringExtra(
					"LapIdNfcKitError"
				), LapidNfcKitError::class.java
			)

			showToast("Something went wrong: ${lapIdNfcKitError.errorException.message}")
		}
	}

	@SuppressLint("UnspecifiedRegisterReceiverFlag")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val receiverFlags = ContextCompat.RECEIVER_EXPORTED
		ContextCompat.registerReceiver(
			this, receiverLapIdNfcKitResult, IntentFilter("LapIdNfcKitResult"), receiverFlags
		)
		ContextCompat.registerReceiver(
			this, receiverLapIdNfcKitError, IntentFilter("LapIdNfcKitError"), receiverFlags
		)

		setContent {
			LapIDNfcKitSampleTheme {
				Surface(
					modifier = Modifier.fillMaxSize(),
				) {
					Column(
						modifier = Modifier
							.fillMaxSize()
							.background(
								brush = Brush.verticalGradient(
									colors = listOf(
										LightBlue, DarkBlue
									)
								)
							),
						verticalArrangement = Arrangement.Center,
						horizontalAlignment = Alignment.CenterHorizontally,
					) {
						Spacer(Modifier.weight(0.25f))

						Image(
							painter = painterResource(id = R.drawable.logo_lapid_white),
							contentDescription = stringResource(id = de.lapid.nfckit.R.string.lapidNfcKit_allTitle),
							modifier = Modifier
								.fillMaxWidth()
								.padding(0.dp, 0.dp, 0.dp, 32.dp)
						)

						Text(
							text = stringResource(R.string.title),
							style = MaterialTheme.typography.titleMedium,
							modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 64.dp)
						)

						Button(textResource = R.string.device_capability_has_camera, onClick = {
							val cameraAvailable = NfcCapability.hasCamera(applicationContext)
							val availability = if (cameraAvailable) {
								"Yes"
							} else {
								"No"
							}

							showToast("Camera is available: $availability")
						})

						Button(textResource = R.string.device_capability_has_nfc, onClick = {
							val nfcAvailable = NfcCapability.hasNfc(applicationContext)
							val availability = if (nfcAvailable) {
								"Yes"
							} else {
								"No"
							}

							showToast("Nfc is available: $availability")
						})

						Button(textResource = R.string.start_nfc_kit, onClick = {
							startActivity(
								Intent(
									applicationContext, LapIDNfcKitMainActivity::class.java
								)
							)
						})

						Spacer(Modifier.weight(0.75f))
					}
				}
			}
		}
	}

	override fun onDestroy() {
		super.onDestroy()

		unregisterLocalReceiver(receiverLapIdNfcKitResult)
		unregisterLocalReceiver(receiverLapIdNfcKitError)
	}

	private fun showToast(text: String) {
		val duration = Toast.LENGTH_SHORT
		val toast = Toast.makeText(this, text, duration)
		toast.show()
	}

	private fun unregisterLocalReceiver(receiver: BroadcastReceiver) {
		try {
			unregisterReceiver(receiver)
		} catch (exception: Exception) {
			//Ignore; Receiver was not registered
		}
	}
}