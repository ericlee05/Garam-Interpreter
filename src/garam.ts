export default class Garam {
    private Storage: Array<number> = Array.apply(null, Array(255)).map(() => { return 0 })
    private Pointer: number = 0

    Interpret(Code: string, onInputEvent: (ApplyValue: () => {}) => void, onOutputEvent: (Output: string) => void) {
        this.CoreInterpret(Code, (ExecCode: ExecuteCode, Pointer: number) => {
            switch (ExecCode) {
                case ExecuteCode.Input:

                    break
                case ExecuteCode.Output:
                    onOutputEvent(String.fromCharCode(this.Storage[Pointer]))
                    break
            }
        })
    }

    Execute(Code: string) {
        this.CoreInterpret(Code, (ExecCode: ExecuteCode, Pointer: number) => {
            switch (ExecCode) {
                case ExecuteCode.Input:

                    break
                case ExecuteCode.Output:
                    console.log(String.fromCharCode(this.Storage[Pointer]))
                    break
            }
        })
    }

    private async CoreInterpret(Code: string, onExecuteCode: (Code: ExecuteCode, Pointer: number) => void) {
        var InterpretIndex = 0
        while (InterpretIndex <= Code.length) {
            const Char = Code[InterpretIndex]
            switch (Char) {
                case "ㅗ":
                    this.Pointer++
                    break
                case "ㅜ":
                    this.Pointer--
                    break
                case "ㅏ":
                    this.Storage[this.Pointer]++
                    break
                case "ㅓ":
                    this.Storage[this.Pointer]--
                    break
                case "ㅇ":
                    onExecuteCode(ExecuteCode.Output, JSON.parse(JSON.stringify(this.Pointer)) as number)
                    break
                case "ㅁ":
                    await onExecuteCode(ExecuteCode.Input, JSON.parse(JSON.stringify(this.Pointer)) as number)
                    break
                case "ㅕ":
                    if (this.Storage[this.Pointer] != 0) { InterpretIndex = this.getNearbyWhileStart(Code, InterpretIndex) } else {
                        InterpretIndex++
                    }
                    break
                case "ㅑ":
                    if (this.Storage[this.Pointer] == 0) { InterpretIndex = this.getNearbyWhileEnd(Code, InterpretIndex) } else {
                        InterpretIndex++
                    }
                    break
            }
            if (Char != "ㅑ" && Char != "ㅕ") { InterpretIndex++ }
        }
    }

    private getNearbyWhileStart(Code: String, Position: number): number {
        for (var i = Position; i != 0; i--) {
            if (Code[i] == "ㅑ") { return i }
        }
        return -1
    }

    private getNearbyWhileEnd(Code: String, Position: number): number {
        for (var i = Position; i != Code.length; i++) {
            if (Code[i] == "ㅕ") { return i }
        }
        return -1
    }


}

enum ExecuteCode {
    Input,
    Output
}