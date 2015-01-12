/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.script;

@SuppressWarnings("serial")
public class InterpreteException extends Exception {
    private String repertoire;
  
	public InterpreteException(Exception ex) {
		super(ex);
	}
	public InterpreteException(String string) {
		super(string);
	}

	/**
	 * @param string
	 * @param e
	 */
	public InterpreteException(String string, Exception e) {
		super(string, e);
	}
}
