import KoreanString.Companion.HangultoJaso
import java.io.File

fun main(args:Array<String>)
{
    println("가람 Interpreter\n해당 인터프리터는 조선이 반포하였습니다.")

    //코드파일 불러오기라면
    if(args.size > 0)
    {
        Interpret(HangultoJaso(File(args[0]).readText())) //파일에서 불러오기
    }
    else //REPL이면
    {
        while(true)
        {
            print("\nREPL >>")
            Interpret(HangultoJaso(readLine()!!)) //입력받아서 코드 실행하기
        }
    }
}

//포인터 전역변수로 생성
var Pointer = VirtualPointer()

fun Interpret(Code:String)
{
    var CodeIndex:Int = 0 //코드 Index
    var LoopIndex:Int = 0 //Loop문을 위한 Index
    while(CodeIndex != Code.length)
    {
        //println("Char Index : ${Pointer.getValue()}")
        when(Code[CodeIndex].toString())
        {
            "ㅗ" -> {Pointer.Index++}
            "ㅜ" -> {Pointer.Index--}
            "ㅏ" -> {Pointer.setValue(Pointer.getValue() + 1)}
            "ㅓ" -> {Pointer.setValue(Pointer.getValue() - 1)}
            "ㅇ" -> {print(KoreanString.toASCII(Pointer.getValue()))}
            "ㅁ" -> {
                print("\n>>")
                Pointer.setValue(readLine()!![0].toChar().toInt())
            }
            "ㅑ" -> {
                LoopIndex = CodeIndex //루프 복귀를 위한 구문
                if(Pointer.getValue() == 0)
                {
                    while(Code[CodeIndex].toString() != "ㅕ"){CodeIndex++}
                }

            }
            "ㅕ" -> {
                if(Pointer.getValue() != 0)
                {
                    CodeIndex = LoopIndex //원래로 돌아가기
                }
            }
        }

        CodeIndex++
    }
}