/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.script;

public class InterpreteIdentifier implements Interprete {
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
		this.pos = pos;
		
		int i = pos;
		int start = pos;
		if (Character.isLetter(text.charAt(i))) {
			i++;
			while (i < text.length()
					&& Character.isLetterOrDigit(text.charAt(i)))
				i++;
			this.pos = i;
			return text.substring(start, this.pos);//new Identifier(text.substring(pos, i), null, Double.class);
		}
		return "";
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
