package de.lapid.lapidnfckitsample.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.lapid.lapidnfckitsample.ui.theme.LightBlue

@Composable
fun Button(
	modifier: Modifier = Modifier,
	textResource: Int,
	onClick: () -> Unit
) {
	Button(
		modifier = modifier
			.fillMaxWidth()
			.height(64.dp)
			.padding(16.dp, 0.dp, 16.dp, 16.dp),
		shape = RoundedCornerShape(4.dp),
		colors = ButtonDefaults.buttonColors(containerColor = LightBlue),
		onClick = onClick
	) {
		Text(
			text = stringResource(textResource),
			fontSize = 18.sp,
			fontWeight = FontWeight.Bold,
			color = Color.White
		)
	}
}
