/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <manuel.dahmen@gmail.com>

***/


package info.emptycanvas.library.tribase.equationeditor;

import java.util.ArrayList;

/**
 *
 * @author Manuel Dahmen <manuel.dahmen@gmail.com>
 */
public class AnalyseurEquation {

    private boolean isNombre(String expr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean isVariable(String expr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean isOperateur(String expr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public interface Symbole
    {
        public String getRepr();
        public double evaluer();
    }
    public class Variable implements Symbole
    {
        private String name;
        private double value;

        public Variable(String name, double value) {
            this.name = name;
            this.value = value;
        }

        
        
        public String getRepr() {
            return name+":"+value;
        }

        public double evaluer() {
            return value;
        }
    }
    public abstract class OperateurBinaire implements Symbole
    {
        protected String repre;
        public Symbole operandeA;
        public Symbole operandeB;

        public OperateurBinaire(Symbole operandeA, Symbole operandeB) {
            this.repre = repre;
            this.operandeA = operandeA;
            this.operandeB = operandeB;
        }
        
    }
    abstract class OperateurUnaire implements Symbole
    {
        protected String repre;
        public Symbole operandeA;

        public OperateurUnaire(Symbole operandeA) {
            this.repre = repre;
            this.operandeA = operandeA;
        }
        
    }
    public class PlusOp extends OperateurBinaire
    {

        public PlusOp(Symbole operandeA, Symbole operandeB) {
            super(operandeA, operandeB);
        }

        public String getRepr() {
            return "+";
        }

        public double evaluer() {
            return operandeA.evaluer()+operandeB.evaluer();
        }
        
    }
    public class PlusOpU extends OperateurUnaire
    {

        public PlusOpU(Symbole operandeA) {
            super(operandeA);
        }

        public String getRepr() {
            return "-";
        }

        public double evaluer() {
            return operandeA.evaluer();
        }
        
    }
    public class MoinsOp extends OperateurBinaire
    {

        public MoinsOp(Symbole operandeA, Symbole operandeB) {
            super(operandeA, operandeB);
        }

        public String getRepr() {
            return "-";
        }

        public double evaluer() {
            return operandeA.evaluer()-operandeB.evaluer();
        }
        
        
    }
    public class MoinsOpU extends OperateurUnaire
    {

        public MoinsOpU(Symbole operandeA) {
            super(operandeA);
        }

        public String getRepr() {
            return "-";
        }

        public double evaluer() {
            return -operandeA.evaluer();
        }
        
    }
    public class Parenthese extends OperateurUnaire
    {

        public Parenthese(Symbole operandeA) {
            super(operandeA);
        }

        public String getRepr() {
            return "()";
        }

        public double evaluer() {
            return operandeA.evaluer();
        }
        
    }
    public class Mult extends OperateurBinaire
    {

        public Mult(Symbole operandeA, Symbole operandeB) {
            super(operandeA, operandeB);
        }
        public String getRepr() {
            return "*";
        }

        public double evaluer() {
            return operandeA.evaluer();
        }
        
    }
    public class Div extends OperateurBinaire
    {

        public Div(Symbole operandeA, Symbole operandeB) {
            super(operandeA, operandeB);
        }
        public String getRepr() {
            return "/";
        }

        public double evaluer() {
            return operandeA.evaluer();
        }
        
    }
    private ArrayList<Symbole> pile = new ArrayList<Symbole>();
    /*
    
    public Symbole topOf;

    private String varNameLetter;
    
    public void analyse(String expression)
    {
        expression = expression.trim().toLowerCase();
        
        varNameLetter = expression.substring(0, 1);
        
        expression = expression.substring(1).trim();
        
        if(expression.substring(0,1).equals("="))
        {
            analyseExpression(trim(expression.substring(1)));
            
        }
        
    }

    private Symbole analyseExpression(String expr, ArrayList<Symbole> pile) 
    {
        if(expr.startsWith("-"))
        {
            return  new MoinsOpU(analyseExpression(trim(expr.substring(1))), new ArrayList<Symbole>());
        }
        else if(expr.startsWith("+"))
        {
            return new PlusOpU(analyseExpression(trim(expr.substring(1))));
        }else if(expr.startsWith("("))
        {
            return new Parenthese(analyseExpression(expr.substring(0, matching(0, expr))));
        }else if(isNombre(expr))
        {
            pile.add(nombre(expr));
            analyserEx
        }
         else if(isVariable(expr))
        {
            pile.add(variable(expr));
        }
        else if(isOperateur(expr))
        {
            pile.add(operateur(expr));
        }
        
    }
    public int matching(int pos0, String exp)
    {
         int level = 1;
         int strI = 1;
         while(level>0 && strI<exp.length())
         {
             if(exp.charAt(strI)=='(')
                level ++;
                else
             if(exp.charAt(strI)==')')
                level --;
                
         }
         return strI;
    }
    private String trim(String substring) {
        return substring.trim();
    }
    private Symbole variable(String trim) {
        if(trim(trim).matches("[a-z"));
        return null;
        
    }

    private Symbole operateur(String trim) {
            char v = operateur(trim);
            if(v=='+')
            {
                pile.add(new PlusOp(null, null));
            }
            if(v=='-')
            {
                pile.add(new MoindOp(null, null));
            }
            if(v=='/')
            {
                pile.add(new DivOp(null, null));
            }
            if(v=='*')
            {
                pile.add(new MultOp(null, null));
            }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Symbole nombre(String trim) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
*/
    }
