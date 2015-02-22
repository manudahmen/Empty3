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
    int pos = 0;
    private int isNombre(String expr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int isVariable(String expr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int isOperateur(String expr) {
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
    public class ParentheseOuvrante extends OperateurUnaire
    {

        public ParentheseOuvrante(Symbole operandeA) {
            super(operandeA);
        }

        public String getRepr() {
            return "(";
        }

        public double evaluer() {
            return operandeA.evaluer();
        }
        
    }
    public class ParentheseFermante extends OperateurUnaire
    {

        public ParentheseFermante(Symbole operandeA) {
            super(operandeA);
        }

        public String getRepr() {
            return ")";
        }

        public double evaluer() {
            throw new UnsupportedOperationException("");        }
        
    }
    
    public class MultOp extends OperateurBinaire
    {

        public MultOp(Symbole operandeA, Symbole operandeB) {
            super(operandeA, operandeB);
        }
        public String getRepr() {
            return "*";
        }

        public double evaluer() {
            return operandeA.evaluer();
        }
        
    }
    public class DivOp extends OperateurBinaire
    {

        public DivOp(Symbole operandeA, Symbole operandeB) {
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
    
    
    public Symbole topOf;

    private String varNameLetter;
    
    public void analyse(String expression)
    {
        expression = expression.trim().toLowerCase();
        
        varNameLetter = expression.substring(0, 1);
        
        expression = expression.substring(1).trim();
        
        if(expression.substring(0,1).equals("="))
        {
            analyseLexicale(trim(expression.substring(1)));
            
        }
        
    }

    private void analyseLexicale(String expr) 
    {
        while(expr!=null&&!"".equals(expr))
        {
        int pos = 0;
        if(expr.startsWith("-"))
        {
            pile.add(new MoinsOpU(null));
            pos = 1;
        }
        else if(expr.startsWith("+"))
        {
            pile.add(new PlusOpU(null));
            pos = 1;
        }else if(expr.startsWith("("))
        {
            pile.add(new ParentheseOuvrante(null));
            pos = 1;
        }else if(expr.startsWith(")"))
        {
            pile.add(new ParentheseFermante(null));
            pos = 1;
        }else if((pos=isNombre(expr))>=0)
        {
            pile.add(nombre(expr));
            pos = 1;
        }
         else if((pos=isVariable(expr))>0)
        {
            pile.add(variable(expr));
            pos = 1;
        }
        else if((pos=isOperateur(expr))>0)
        {
            pile.add(operateur(expr));
            pos = 1;
        }
            expr = expr.substring(pos).trim();
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
        if(trim(trim).matches("[a-z]"))
            return new Variable(""+trim.charAt(0), 0);
        return null;
        
    }

    private Symbole operateur(String trim) {
        char v = trim.charAt(0);
        if(v=='+')
            {
                pile.add(new PlusOp(null, null));
            }
            if(v=='-')
            {
                pile.add(new MoinsOp(null, null));
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

    }
