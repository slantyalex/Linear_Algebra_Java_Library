package util;

public class LateXDocument{
    private StringBuilder body = new StringBuilder();

    public void add(String latexExpression){
        body.append(latexExpression).append("\n\n");
    }

    public String build(){
        return "\\documentclass{article}\n"
             + "\\usepackage{amsmath}\n"
             + "\\begin{document}\n\n"
             + body.toString()
             + "\n\\end{document}\n";
    }
}