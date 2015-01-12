/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.script;

public interface Interprete {

    public InterpreteConstants constant();

    public int getPosition();

    public Object interprete(String text, int pos) throws InterpreteException;

    public void setConstant(InterpreteConstants c);

    public void setRepertoire(String r);

}
