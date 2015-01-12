/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.script;

public class InterpreteDouble implements Interprete{
    private String repertoire;
    @Override
	public InterpreteConstants constant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object interprete(String text, int pos) {
		Double d = Double.parseDouble(text);
		return d!=null ? d : new Throwable("Erreur d'analyse de nombre");
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
