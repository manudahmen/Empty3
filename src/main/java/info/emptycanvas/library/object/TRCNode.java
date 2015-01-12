package info.emptycanvas.library.object;

import java.util.HashSet;
import java.util.Set;

public class TRCNode {
	private Set<TRCNode> mc = null;
	private Representable value;

	public TRCNode() {
		mc = new HashSet<TRCNode>();

	}
	
	
	public TRCNode(Representable object) {
		this();
		value = object;
	}

	public Set<TRCNode> getValueSet() {
		return mc;
	}

	}
