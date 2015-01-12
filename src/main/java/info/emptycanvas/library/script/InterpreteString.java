/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.script;

public class InterpreteString implements Interprete {
    private String repertoire;

        private int pos;
@Override
public InterpreteConstants constant() {
	// TODO Auto-generated method stub
	return null;
}

	@Override
	public int getPosition() {
		return pos;
	}

	@Override
	public Object interprete(String text, int pos) throws InterpreteException {
		String res = "";
		if (Character.isLetter(text.charAt(pos))) {
			res += text.charAt(pos);
			pos++;
			while (pos < text.length() && Character.isLetterOrDigit(text.charAt(pos))) {
				res += text.charAt(pos);
				pos++;
			}
		}
		this.pos = pos;
		return res;
	}

	@Override
	public void setConstant(InterpreteConstants c) {
		// TODO Auto-generated method stub

	}

	@Override
   public void setRepertoire(String r) {
	this.repertoire = r;
   }

}
