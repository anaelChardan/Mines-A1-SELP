package parser;

public class FunctionName extends AST {
    public String value;

    public FunctionName(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FunctionName(" + value + ")";
    }
}
