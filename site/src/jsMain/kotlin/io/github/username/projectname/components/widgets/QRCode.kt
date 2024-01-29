package io.github.username.projectname.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.silk.components.graphics.Image
import io.github.username.projectname.Config


/*
Defines the error correction code (ECC) which determines the degree of data redundancy.
The more data redundancy exists, the more data can be restored if a QR code is damaged
(i.e. scratches on a QR code sticker or something like that).
 */
enum class ErrorCorrectionCode(val char: Char) {
    LOW('L'),
    MIDDLE('M'),
    QUALITY('Q'),
    HIGH('H'),
}

enum class QRCodeImageFormat(val value: String) {
    PNG("png"),
    GIF("gif"),
    JPEG("jpeg"),
    JPG("jpg"),
    SVG("svg"),
    EPS("eps"),
}

// NOTE: Minimal size is 10x10.
@Composable
fun QRCode(
    data: String = Config.Socials.SITE_URL,
    modifier: Modifier = Modifier,
    width: Int = 100,
    height: Int = 100,
    errorCorrection: ErrorCorrectionCode = ErrorCorrectionCode.LOW,
    dataColor: String = "0-0-0", // Should be hex or rgb representation as string.
    backgroundColor: String = "255-255-255", // Should be hex or rgb representation as string.
    margin: Int = 1, // Thickness of a margin in pixels.
    qzone: Int = 0, // Thickness of a margin (=“quiet zone”, an area without disturbing elements to help readers locating the QR code), in modules as measuring unit.
    format: QRCodeImageFormat = QRCodeImageFormat.SVG,
) {
    println("Generating QR Code for $data")
    val source =
        "http://api.qrserver.com/v1/create-qr-code/?data=${data}&size=${width}x${height}&ecc=${errorCorrection}&color=${dataColor}&bgcolor=${backgroundColor}&margin=${margin}&qzone=${qzone}&format=${format.value}"

    println("Final result for QR Code was $source")
    Image(src = source, modifier = modifier)
}