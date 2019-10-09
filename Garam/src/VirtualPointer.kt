class VirtualPointer
{
    var Index:Int = 0
    private var Value = arrayOfNulls<Int>(1000) //1000개의 데이터 공간

    //값 설정
    fun setValue(value:Int)
    {
        Value[Index] = value
    }

    //값 가져오기
    fun getValue():Int
    {
        if(Value[Index] == null) //Null이면
        {
            return 0
        }
        else //Null이 아니면
        {
            return Value[Index]!!
        }
    }

}