package com.michaelrmossman.multiplatform.discover.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import co.touchlab.kermit.Logger
import discovermultiplatform.composeapp.generated.resources.Res
import discovermultiplatform.composeapp.generated.resources.route_no_data
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.StringResource

@Composable
private fun getFormattedBoldText(
    fullText: String, boldText: String
) : AnnotatedString {
    val start = fullText.indexOf(boldText)
    val end = start.plus(boldText.length)
    val spanStyles = listOf(
        AnnotatedString.Range(
            SpanStyle(fontWeight = FontWeight.Bold),
            start = start,
            end = end
        )
    )
    return AnnotatedString(
        text = fullText,
        spanStyles = spanStyles
    )
}

@Composable
fun getTextWithPlaceholder(
    resource: StringResource,
    string: String?
) : AnnotatedString {
    val fullText = stringResource(
        resource = resource,
        /* Note elvis op */
        formatArgs = arrayOf(string ?: stringResource(
            resource = Res.string.route_no_data
        ))
    )
    val boldText = fullText.substring(
        0, fullText.indexOf(":")
    )
    return getFormattedBoldText(
        fullText, boldText
    )
}

/* This is a temp workaround for the new JetBrains
   resources API, where line breaks and indents
   within string resources show as-is in the UI */
suspend fun getTrimmedString(
    resource: StringResource, vararg formatArgs: Any
) : String {
    val string = getString(
        resource = resource,
        formatArgs = formatArgs
    )
    return when (string.contains("\n")) {
        false -> string
        else -> when (
            string.contains("  ") // Two spaces
        ) {
            false -> string.replace(
                "\n"," " // One space
            )
            else -> {
                val sections =
                    string.split("  ") // Two spaces
                val sb = StringBuilder()
                sections.forEach { section ->
                    sb.append(
                        section
                            // Line breaks with One space
                            .replace("\n"," ")
                             // Two spaces with One space
                            .replace("  "," ")
                    )
                }
                sb.toString()
            }
        }
    }
}