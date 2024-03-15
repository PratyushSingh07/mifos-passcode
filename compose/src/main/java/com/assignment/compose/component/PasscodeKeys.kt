package com.assignment.compose.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.assignment.compose.viewmodels.PasscodeViewModel
import com.assignment.compose.R
import com.assignment.compose.theme.PasscodeKeyButtonStyle
import com.assignment.compose.theme.keyTint
import com.assignment.compose.utility.PreferenceManager

@Composable
fun PasscodeKeys(
    viewModel: PasscodeViewModel,
    modifier: Modifier = Modifier,
) {
    val onEnterKeyClick = { keyTitle: String ->
        viewModel.enterKey(keyTitle)
    }
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            PasscodeKey(
                modifier = Modifier.weight(weight = 1.0F),
                keyTitle = "1",
                onClick = onEnterKeyClick
            )
            PasscodeKey(
                modifier = Modifier.weight(weight = 1.0F),
                keyTitle = "2",
                onClick = onEnterKeyClick
            )
            PasscodeKey(
                modifier = Modifier.weight(weight = 1.0F),
                keyTitle = "3",
                onClick = onEnterKeyClick
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            PasscodeKey(
                modifier = Modifier.weight(weight = 1.0F),
                keyTitle = "4",
                onClick = onEnterKeyClick
            )
            PasscodeKey(
                modifier = Modifier.weight(weight = 1.0F),
                keyTitle = "5",
                onClick = onEnterKeyClick
            )
            PasscodeKey(
                modifier = Modifier.weight(weight = 1.0F),
                keyTitle = "6",
                onClick = onEnterKeyClick
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            PasscodeKey(
                modifier = Modifier.weight(weight = 1.0F),
                keyTitle = "7",
                onClick = onEnterKeyClick
            )
            PasscodeKey(
                modifier = Modifier.weight(weight = 1.0F),
                keyTitle = "8",
                onClick = onEnterKeyClick
            )
            PasscodeKey(
                modifier = Modifier.weight(weight = 1.0F),
                keyTitle = "9",
                onClick = onEnterKeyClick
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            PasscodeKey(modifier = Modifier.weight(weight = 1.0F))
            PasscodeKey(
                modifier = Modifier.weight(weight = 1.0F),
                keyTitle = "0",
                onClick = onEnterKeyClick
            )
            PasscodeKey(
                modifier = Modifier.weight(weight = 1.0F),
                keyIcon = ImageVector.vectorResource(id = R.drawable.ic_delete),
                keyIconContentDescription = "Delete Passcode Key Button",
                onClick = {
                    viewModel.deleteKey()
                },
                onLongClick = {
                    viewModel.deleteAllKeys()
                }
            )
        }
    }
}

@Composable
fun PasscodeKey(
    modifier: Modifier = Modifier,
    keyTitle: String = "",
    keyIcon: ImageVector? = null,
    keyIconContentDescription: String = "",
    onClick: ((String) -> Unit)? = null,
    onLongClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        CombinedClickableIconButton(
            modifier = Modifier
                .padding(all = 4.dp),
            onClick = {
                onClick?.invoke(keyTitle)
            },
            onLongClick = {
                onLongClick?.invoke()
            }
        ) {
            if (keyIcon == null) {
                Text(
                    text = keyTitle,
                    style = PasscodeKeyButtonStyle.copy(color = keyTint)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Backspace,
                    contentDescription = keyIconContentDescription,
                    tint = keyTint
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CombinedClickableIconButton(
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    rippleRadius: Dp = 36.dp,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .size(size = size)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    bounded = false,
                    radius = rippleRadius,
                    color = Color.Cyan
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val contentAlpha =
            if (enabled) LocalContentColor.current else LocalContentColor.current.copy(alpha = 0f)
        CompositionLocalProvider(LocalContentColor provides contentAlpha, content = content)
    }
}


@Preview
@Composable
fun PasscodeKeysPreview() {
    PasscodeKeys(viewModel = PasscodeViewModel(PreferenceManager(LocalContext.current)))
}