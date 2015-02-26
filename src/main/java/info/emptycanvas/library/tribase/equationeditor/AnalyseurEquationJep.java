/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <manuel.dahmen@gmail.com>

***/


package info.emptycanvas.library.tribase.equationeditor;

import org.nfunk.jep.JEP;

/**
 *
 * @author Manuel Dahmen <manuel.dahmen@gmail.com>
 */
public class AnalyseurEquationJep {
    private final JEP myParser;
    private String strParser;

    public AnalyseurEquationJep(String eq) {
        myParser = new org.nfunk.jep.JEP();
       myParser.addStandardFunctions();
       myParser.addStandardConstants();
       strParser = eq;
    }
   public void parse(String parse)
   {
       strParser = parse;
   }
   public double value()
   {
       myParser.parseExpression(strParser);
       return myParser.getValue();
   }
   public void setVariable(String name, double v)
   {
       myParser.addVariable(name, v);
   }
   public void setContant(String name, double v)
   {
       myParser.addVariable(name, v);
   }
    public static void main(String[] args) {
        
    AnalyseurEquationJep anlayseurEquationJep = new AnalyseurEquationJep("a*x+b*b");

    anlayseurEquationJep.setContant("a", 1);
    anlayseurEquationJep.setContant("b", 2);
    
    
    anlayseurEquationJep.setContant("x", 3);
    System.out.println("Result: " + anlayseurEquationJep.value()+ "( expected: 7");
    anlayseurEquationJep.setContant("x", 4);
    System.out.println("Result: " + anlayseurEquationJep.value() + "(expected= 8");
    anlayseurEquationJep.setContant("x", 5);
    System.out.println("Result: " + anlayseurEquationJep.value() + "(expected= 9");
    
    
    }
    
    
}
