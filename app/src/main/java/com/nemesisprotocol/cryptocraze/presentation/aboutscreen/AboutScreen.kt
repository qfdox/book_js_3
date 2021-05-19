package com.nemesisprotocol.cryptocraze.presentation.aboutscreen

import android.content.Intent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.alorma.compose.settings.ui.SettingsMenuLink
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

@Composable
fun AboutScreen() {
    val context = LocalContext.current
    SettingsMenuLink(
        title = { Text(text = "Open source libraries") },
        onClick = {
            OssLicensesMenuActivity.setActivityTitle("Open source libraries")
            context.startActivity(Intent(context, OssLicensesMenuActivity::class.java))
        },
    )
}
