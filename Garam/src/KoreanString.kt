class KoreanString
{
    companion object
    {
        private var Chosung = charArrayOf(0x3131.toChar(),
            0x3132.toChar(), 0x3134.toChar(),
            0x3137.toChar(), 0x3138.toChar(),
            0x3139.toChar(), 0x3141.toChar(),
            0x3142.toChar(), 0x3143.toChar(),
            0x3145.toChar(), 0x3146.toChar(),
            0x3147.toChar(), 0x3148.toChar(),
            0x3149.toChar(), 0x314a.toChar(),
            0x314b.toChar(), 0x314c.toChar(),
            0x314d.toChar(), 0x314e.toChar())

        private var jwungsung = charArrayOf(
            0x314f.toChar(), 0x3150.toChar(),
            0x3151.toChar(), 0x3152.toChar(),
            0x3153.toChar(), 0x3154.toChar(),
            0x3155.toChar(), 0x3156.toChar(),
            0x3157.toChar(), 0x3158.toChar(),
            0x3159.toChar(), 0x315a.toChar(),
            0x315b.toChar(), 0x315c.toChar(),
            0x315d.toChar(), 0x315e.toChar(),
            0x315f.toChar(), 0x3160.toChar(),
            0x3161.toChar(), 0x3162.toChar(),
            0x316.toChar()
        )

        private var jongsung = charArrayOf(
            0x3131.toChar(), 0x3132.toChar(),
            0x3133.toChar(), 0x3134.toChar(),
            0x3135.toChar(), 0x3136.toChar(),
            0x3137.toChar(), 0x3139.toChar(),
            0x313a.toChar(), 0x313b.toChar(),
            0x313c.toChar(), 0x313d.toChar(),
            0x313e.toChar(), 0x313f.toChar(),
            0x3140.toChar(), 0x3141.toChar(),
            0x3142.toChar(), 0x3144.toChar(),
            0x3145.toChar(), 0x3146.toChar(),
            0x3147.toChar(), 0x3148.toChar(),
            0x314a.toChar(), 0x314b.toChar(),
            0x314c.toChar(), 0x314d.toChar(),
            0x314e.toChar()
        )

        fun HangultoJaso(Text:String):String
        {
            var Result = ""
            for(TextChr in Text)
            {
                if(TextChr >= 0xAC00.toChar() && TextChr <= 0xD7A3.toChar())
                {
                    var c = (TextChr - 0xAC00).toInt()
                    var a = c/(21 * 28)
                    c = c % (21 * 28)
                    var b = c / 28
                    c = c % 28
                    Result = Result + Chosung[a] + jwungsung[b]
                    if(c != 0){Result = Result + jongsung[c]}
                }
                else
                {
                    Result = Result + TextChr
                }
            }

            return Result
        }

        fun toASCII(value: Int): String {
            val length = 4
            val builder = StringBuilder(length)
            for (i in length - 1 downTo 0) {
                builder.append((value shr 8 * i and 0xFF).toChar())
            }
            return builder.toString()
        }

    }
}