package info.emptycanvas.library.object;

public class TCRCTree {
	private TRCNode root;
	
	public TCRCTree() {
		root = new TRCNode(null);
	}
	
	public TRCNode getRoot() {
		return root;
	}
	public void setRoot(TRCNode root) {
		this.root = root;
	}
}
