package com.michaelrmossman.multiplatform.discover.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import co.touchlab.kermit.Logger
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import discovermultiplatform.composeapp.generated.resources.Res
import discovermultiplatform.composeapp.generated.resources.route_no_data
import discovermultiplatform.composeapp.generated.resources.subtitle_community
import discovermultiplatform.composeapp.generated.resources.subtitle_faves
import discovermultiplatform.composeapp.generated.resources.subtitle_transit
import discovermultiplatform.composeapp.generated.resources.subtitle_walks
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
fun getTabSubtitle(index: UShort): String {
    val subtitles = listOf(
        stringResource(resource = Res.string.subtitle_community),
        stringResource(resource = Res.string.subtitle_transit),
        stringResource(resource = Res.string.subtitle_walks),
        stringResource(resource = Res.string.subtitle_faves)
    )
    return subtitles[index.toInt()]
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
    return when (val colon = fullText.indexOf(":")) {
        -1 -> AnnotatedString(text = fullText)
        else -> {
            val boldText = fullText.substring(
                0, colon
            )
            getFormattedBoldText(
                fullText = fullText,
                boldText = boldText
            )
        }
    }
}

/* This is a temp workaround for the new JetBrains
   resources API, where line breaks and indents
   within string resources show as-is in the UI */
@NativeCoroutines
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

object TextUtils {

//    private fun getFormattedBoldText(
//        fullText: String, boldText: String
//    ) : AnnotatedString {
//        val start = fullText.indexOf(boldText)
//        val end = start.plus(boldText.length)
//        val spanStyles = listOf(
//            AnnotatedString.Range(
//                SpanStyle(fontWeight = FontWeight.Bold),
//                start = start,
//                end = end
//            )
//        )
//        return AnnotatedString(
//            text = fullText,
//            spanStyles = spanStyles
//        )
//    }

    fun getPrettyTextFromEnum(name: String?): String? {
        val prettyText: String?

        when (name) { // e.g. enum = CommunityFacilities
            null -> prettyText = null
            else -> {
                /* Retain for uppercase char */
                val initial = name.substring(
                    0, 1
                )

                name.substring(1).replace(
                    /* Find subsequent uppercase
                       chars & insert a space */
                    "([A-Z])".toRegex(),
                    " $1" // Note preceding space
                ).let { regex ->
                    prettyText = "$initial$regex"
                }
            }
        }

        // prettyText?.let { desc -> Logger.d("HEY") { desc } }
        return prettyText
    }

//    suspend fun getTextWithPlaceholder(
//        resource: StringResource,
//        string: String?
//    ) : AnnotatedString {
//        val fullText = getString(
//            resource = resource,
//            /* Note elvis op */
//            formatArgs = arrayOf(string ?: getString(
//                resource = Res.string.route_no_data
//            ))
//        )
//        return when (val colon = fullText.indexOf(":")) {
//            -1 -> AnnotatedString(text = fullText)
//            else -> {
//                val boldText = fullText.substring(
//                    0, colon
//                )
//                getFormattedBoldText(
//                    fullText = fullText,
//                    boldText = boldText
//                )
//            }
//        }
//    }
}