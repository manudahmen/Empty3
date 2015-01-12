/*
 * 2013 Manuel Dahmen
 */

package info.emptycanvas.library.script;

import info.emptycanvas.library.extra.CollineModele3;
import info.emptycanvas.library.extra.AttracteurEtrange;
import info.emptycanvas.library.extra.Tourbillon;
import info.emptycanvas.library.extra.SimpleSphereAvecTexture;
import info.emptycanvas.library.extra.CollineModele2;
import info.emptycanvas.library.extra.CollineModele1;
import info.emptycanvas.library.extra.SimpleSphere;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Tetraedre;
import info.emptycanvas.library.object.TRI;
import info.emptycanvas.library.object.BezierCubique;
import info.emptycanvas.library.object.PolyMap;
import info.emptycanvas.library.object.Cube;
import info.emptycanvas.library.object.Barycentre;
import info.emptycanvas.library.object.TColor;
import info.emptycanvas.library.object.ID;
import info.emptycanvas.library.object.Camera;
import info.emptycanvas.library.object.Polygone;
import info.emptycanvas.library.object.TRIObject;
import info.emptycanvas.library.object.Quads;
import info.emptycanvas.library.object.LumierePointSimple;
import info.emptycanvas.library.object.SegmentDroite;
import info.emptycanvas.library.object.Lumiere;
import info.emptycanvas.library.object.Representable;
import info.emptycanvas.library.object.BezierCubique2D;
import info.emptycanvas.library.object.BSpline;
import info.emptycanvas.library.object.LumierePoint;
import be.ibiiztera.md.pmatrix.pushmatrix.base.Nurbs;
import info.emptycanvas.library.tribase.Plan3D;
import info.emptycanvas.library.tribase.TRIEllipsoide;
import info.emptycanvas.library.tribase.TRISphere;
import info.emptycanvas.library.tribase.Tubulaire;
import info.emptycanvas.library.object.RepresentableConteneur;
import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InterpreteFacade {

	public final int FILETYPE_MODEL_TO = 0;
	public final int BLANK = 0;
	public final int LEFTPARENTHESIS = 1;
	public final int RIGHTPARENTHESIS = 2;
	public final int LEFTSKETCH = 3;
	public final int RIGHTSKETCH = 4;
	public final int ALPHA_WORD = 5;
	public final int POINT3D = 100;
	public final int COLOR = 101;
	public final int INTEGER = 102;
	public final int DOUBLE = 103;
	public final int TRIANGLE = 104;
	public final int LIST_TRIANGLES = 105;
	public final int BSPLINE = 106;
	public final int BEZIER = 107;
	private String text;
	private int pos;
	private boolean okay;
	private String repertoire;

	public InterpreteFacade(String text, int pos) {
		super();
		this.text = text;
		this.pos = pos;
	}

	public Object getParsedObject() {
		return null;
	}

	public int getPosition() {
		return pos;
	}

	public String getText() {
		return text;
	}

	public AttracteurEtrange intepreteAttracteurEtrange()
			throws InterpreteException {
		InterpreteAttracteurEtrange interpreteH = new InterpreteAttracteurEtrange();
		interpreteH.setRepertoire(repertoire);
		AttracteurEtrange t = null;
		try {
			t = (AttracteurEtrange) interpreteH.interprete(text, pos);
			pos = interpreteH.getPosition();
		} catch (Exception e) {
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpreteLangage")
							.getString(
									"ATTRACTEUR ETRANGE ERRUER D'ANALYSE SYNTAXIQUE"));

		}
		return t;
	}

	Representable intepreteColline() throws InterpreteException {
		InterpretesBase interpreteH = new InterpretesBase();
		ArrayList<Integer> pattern;
		pattern = new ArrayList<Integer>();
		pattern.add(interpreteH.BLANK);
		pattern.add(interpreteH.LEFTPARENTHESIS);
		pattern.add(interpreteH.BLANK);
		pattern.add(interpreteH.INTEGER);
		pattern.add(interpreteH.BLANK);
		pattern.add(interpreteH.RIGHTPARENTHESIS);
		interpreteH.compile(pattern);
		Integer type = (Integer) interpreteH.read(text, pos).get(3);
		this.pos = interpreteH.getPosition();

		switch (type) {
		case 1:
			return new CollineModele1(1000);
		case 2:
			return new CollineModele2(1000);
		case 3:
			return new CollineModele3(1000);
		default:
			return new CollineModele1(1000);
		}
	}

	/**
	 * *
	 * 
	 * @return segment de droite
	 * @throws InterpreteException
	 */
	public SegmentDroite intepreteSegmentDroite() throws InterpreteException {

		InterpreteSegment interpreteH = new InterpreteSegment();
		interpreteH.setRepertoire(repertoire);
		SegmentDroite t = null;
		try {
			t = (SegmentDroite) interpreteH.interprete(text, pos);
			pos = interpreteH.getPosition();
		} catch (Exception e) {
			throw new InterpreteException(e);

		}
		return t;

	}

	/**
	 * @return @throws InterpreteException
	 */
	public SimpleSphere intepreteSimpleSphere() throws InterpreteException {
		InterpreteSimpleSphere interpreteH = new InterpreteSimpleSphere();
		interpreteH.setRepertoire(repertoire);
		SimpleSphere t = null;
		try {
			t = (SimpleSphere) interpreteH.interprete(text, pos);
			pos = interpreteH.getPosition();
		} catch (InterpreteException e) {
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpreteLangage")
							.getString(
									"SIMPLE SPHERE:  ERREUR D'ANALYSE SYNTAXIQUE"));

		}
		return t;

	}

	public Tourbillon intepreteTourbillon() throws InterpreteException {
		interpreteBlank();
		interpreteParentheseOuvrante();
		interpreteBlank();
		interpreteParentheseFermante();
		interpreteBlank();
		return new Tourbillon();

	}

	public Tubulaire intepreteTubulaire() throws InterpreteException {
		InterpreteTubulaire interpreteH = new InterpreteTubulaire();
		interpreteH.setRepertoire(repertoire);
		Tubulaire t = null;
		try {
			t = (Tubulaire) interpreteH.interprete(text, pos);
			pos = interpreteH.getPosition();
		} catch (Exception e) {
			throw new InterpreteException(e);

		}
		return t;

	}

	public BezierCubique interpreteBezier() throws InterpreteException {
		InterpreteBezier interpreteH = new InterpreteBezier();
		interpreteH.setRepertoire(repertoire);
		BezierCubique b = null;
		try {
			b = (BezierCubique) interpreteH.interprete(text, pos);
			pos = interpreteH.getPosition();
		} catch (Exception e) {
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpreteLangage")
							.getString("ERRUER"));

		}
		return b;
	}

	public BezierCubique2D interpreteBezier2d() throws InterpreteException {
		InterpreteBezier2D interpreteH = new InterpreteBezier2D();
		interpreteH.setRepertoire(repertoire);
		BezierCubique2D b = null;
		try {
			b = (BezierCubique2D) interpreteH.interprete(text, pos);
			pos = interpreteH.getPosition();
		} catch (Exception e) {
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpreteLangage")
							.getString("ERRUER"));

		}
		return b;
	}

	public String interpreteBlank() {
		InterpretesBase ib = new InterpretesBase();
		ArrayList<Integer> pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		try {
			ib.read(text, pos);
			pos = ib.getPosition();
		} catch (Exception e) {
			return "";
		}
		return java.util.ResourceBundle.getBundle(
				"info.emptycanvas.library/scripts/InterpreteLangage")
				.getString(" ");
	}

	public BSpline interpreteBSpline() throws InterpreteException {
		InterpreteBSpline interpreteH = new InterpreteBSpline();
		interpreteH.setRepertoire(repertoire);
		BSpline b = null;
		try {
			b = (BSpline) interpreteH.interprete(text, pos);
			pos = interpreteH.getPosition();
		} catch (Exception e) {
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpreteLangage")
							.getString("ERRUER"));

		}
		return b;
	}

	public Camera interpreteCamera() throws InterpreteException {
		InterpreteCamera interpreteH = new InterpreteCamera();
		Camera c = null;
		try {

			c = (Camera) interpreteH.interprete(text, pos);
			pos = interpreteH.getPosition();
		} catch (InterpreteException ex) {
			throw new InterpreteException("CAMERA ???", ex);
		}
		return c;
	}

	public ArrayList<Camera> interpreteCameraCollection()
			throws InterpreteException {
		ArrayList<Camera> cameras = new ArrayList<Camera>();

		interpreteBlank();
		interpreteParentheseOuvrante();
		interpreteBlank();

		Camera c;
		try {
			while (true) {
				String id = interpreteIdentifier();
				System.out.println(id);
				if ("camera".equals(id == null ? "NULL" : id.toLowerCase())) {
					interpreteBlank();
					c = interpreteCamera();
					cameras.add(c);
					System.out.println(id);
				} else
					break;
				interpreteBlank();
			}
		} catch (InterpreteException ex) {
		}

		interpreteBlank();
		interpreteParentheseFermante();
		interpreteBlank();

		return cameras;
	}

	public Color interpreteColor() throws InterpreteException {
		InterpreteCouleur pc = new InterpreteCouleur();
		Color c = Color.BLACK;
		try {
			c = (Color) pc.interprete(text, pos);
			pos = pc.getPosition();
		} catch (Exception e) {
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpreteLangage")
							.getString("ERRUER"));

		}
		return c;
	}

	public Cube interpreteCube() throws InterpreteException {
		InterpreteCube interpreteH = new InterpreteCube();
		interpreteH.setRepertoire(repertoire);
		Cube c = null;
		try {
			c = (Cube) interpreteH.interprete(text, pos);
			pos = interpreteH.getPosition();
		} catch (Exception e) {
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpreteLangage")
							.getString("CUBE :  ERREUR D'ANALYSE SYNTAXIQUE"),
					e);
		}
		return c;
	}

	public Double interpreteDouble() throws InterpreteException {
		InterpretesBase ib = new InterpretesBase();
		ArrayList<Integer> pattern = new ArrayList<Integer>();
		pattern.add(ib.DECIMAL);
		ib.compile(pattern);
		try {
			ib.read(text, pos);
			pos = ib.getPosition();
		} catch (Exception e) {
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpreteLangage")
							.getString("ERRUER"));

		}
		return (Double) ib.get().get(0);
	}

	public ID interpreteId() throws InterpreteException {
		interpreteBlank();
		interpreteParentheseOuvrante();
		interpreteBlank();
		String id = interpreteIdentifier();
		interpreteBlank();
		interpreteParentheseFermante();
		return new ID(id);
	}

	public String interpreteIdentifier() throws InterpreteException {
		InterpreteString is = new InterpreteString();
		String s = "";
		try {
			s = (String) is.interprete(text, pos);
			pos = is.getPosition();
		} catch (Exception e) {
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpreteLangage")
							.getString("ERRUER"));
		}/*
		 * while (success) { interpreteBlank(); try { s += (String)
		 * is.interprete(text, pos); pos = is.getPosition(); } catch (Exception
		 * e) { throw new InterpreteException("Erruer"); } }
		 */
		return s;
	}

	public Integer interpreteInteger() throws InterpreteException {
		InterpretesBase ib = new InterpretesBase();
		ArrayList<Integer> pattern = new ArrayList<Integer>();
		pattern.add(ib.INTEGER);
		ib.compile(pattern);
		try {
			ib.read(text, pos);
			pos = ib.getPosition();
		} catch (Exception e) {
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpreteLangage")
							.getString("ERRUER"));

		}
		return (Integer) ib.get().get(0);
	}

	ArrayList<Lumiere> interpreteLumiereCollection() throws InterpreteException {
		ArrayList<Lumiere> lumieres = new ArrayList<Lumiere>();
		InterpretesBase ib = new InterpretesBase();
		ArrayList<Integer> pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.LEFTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		setPosition(ib.getPosition());
		Lumiere c;
		try {
			while (true) {
				interpreteBlank();
				String id = interpreteIdentifier();
				interpreteBlank();
				if ("lumierepoint".equals(id == null ? "NULL" : id
						.toLowerCase())) {
					c = interpreteLumierePoint();
					lumieres.add(c);
				}
				interpreteBlank();
			}
		} catch (InterpreteException ex) {
		}

		ib = new InterpretesBase();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.RIGHTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ArrayList<Object> read = ib.read(text, pos);
		setPosition(pos);

		return lumieres;
	}

	public LumierePoint interpreteLumierePoint() throws InterpreteException {
		InterpretesBase ib = new InterpretesBase();
		ArrayList<Integer> pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.LEFTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		setPosition(ib.getPosition());

		// Point de lumiere
		Point3D pl = interpretePoint3D();
		// Intensite au point (*)
		double intensite = 1.0;
		Color c = interpreteColor();

		LumierePointSimple lps = new LumierePointSimple(c, pl, intensite);

		ib = new InterpretesBase();
		pattern = new ArrayList<Integer>();
		pattern.add(ib.BLANK);
		pattern.add(ib.RIGHTPARENTHESIS);
		pattern.add(ib.BLANK);
		ib.compile(pattern);
		ib.read(text, pos);
		setPosition(ib.getPosition());

		return lps;

	}

	public void interpreteMODHomothetie(Representable r) {
	}

	public void interpreteMODRotation(Representable r) {
	}

	public void interpreteMODTranslation(Representable r) {
	}

	Nurbs interpreteNurbs() throws InterpreteException {
       Nurbs n = null;
                InterpreteNurbs iq = new InterpreteNurbs();
                n = (Nurbs) iq.interprete(text, pos);
            return n;
    }

	public void interpreteParentheseFermante() throws InterpreteException {
		InterpretesBase ib = new InterpretesBase();
		ArrayList<Integer> pattern = new ArrayList<Integer>();
		pattern.add(ib.RIGHTPARENTHESIS);
		ib.compile(pattern);
		ib.read(text, pos);
		setPosition(ib.getPosition());
	}

	public void interpreteParentheseOuvrante() throws InterpreteException {
		InterpretesBase ib = new InterpretesBase();
		ArrayList<Integer> pattern = new ArrayList<Integer>();
		pattern.add(ib.LEFTPARENTHESIS);
		ib.compile(pattern);
		ib.read(text, pos);
		setPosition(ib.getPosition());
	}

	public Plan3D interpretePlan3D() throws InterpreteException {
		InterpretePlan3D interpreteH = new InterpretePlan3D();
		interpreteH.setRepertoire(repertoire);
		Plan3D c = null;
		try {
			c = (Plan3D) interpreteH.interprete(text, pos);
			pos = interpreteH.getPosition();
		} catch (Exception e) {
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpreteLangage")
							.getString("PLAN3D :  ERREUR D'ANALYSE SYNTAXIQUE "),
					e);
		}
		return c;
	}

	public Point3D interpretePoint3D() throws InterpreteException {
		InterpretePoint3D pp = new InterpretePoint3D();
		Point3D c = new Point3D();
		try {
			c = (Point3D) pp.interprete(text, pos);
			pos = pp.getPosition();
		} catch (Exception e) {
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpreteLangage")
							.getString("ERRUER"));

		}
		return c;
	}

	public Point3D interpretePoint3DAvecCouleur() throws InterpreteException {
		InterpretePoint3DCouleur ipp = new InterpretePoint3DCouleur();
		Point3D p = null;
		try {
			p = (Point3D) ipp.interprete(text, pos);
			pos = ipp.getPosition();
		} catch (Exception e) {
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpreteLangage")
							.getString("ERRUER"));

		}
		return p;

	}

	public Polygone interpretePolygone() throws InterpreteException {
		InterpretePolygone interpreteH = new InterpretePolygone();
		interpreteH.setRepertoire(repertoire);
		Polygone s = null;
		try {
			s = (Polygone) interpreteH.interprete(text, pos);
			pos = interpreteH.getPosition();
		} catch (InterpreteException ex) {
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpreteLangage")
							.getString(
									"TRISPHERE :  ERREUR D'ANALYSE SYNTAXIQUE "),
					ex);
		}
		return s;
	}

	public PolyMap interpretePolyMapDef() throws InterpreteException {
		InterpretePolyMapDef interpreteH;
                interpreteH = new InterpretePolyMapDef();
		interpreteH.setRepertoire(repertoire);
		PolyMap pm = null;
		try {
			pm = (PolyMap) interpreteH.interprete(text, pos);
			pos = interpreteH.getPosition();
		} catch (InterpreteException ex) {
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpretePolyMap")
							.getString(
									"POLYMAP:  ERREUR D'ANALYSE SYNTAXIQUE "),
					ex);
		}
		return pm;
	}

	public Barycentre interpretePosition() throws InterpreteException {
		InterpretePosition interpreteH = new InterpretePosition();
		interpreteH.setRepertoire(repertoire);
		Barycentre poso = new Barycentre();
		try {
			poso = (Barycentre) interpreteH.interprete(text, pos);
			pos = interpreteH.getPosition();
		} catch (InterpreteException ex) {
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpreteLangage")
							.getString(
									"Position : erreur d'analyse"),
					ex);
		}
		return poso;
	}

	public Quads interpreteQuads() throws InterpreteException {
        Quads q = null;
                InterpreteQuads iq = new InterpreteQuads();
                q = (Quads) iq.interprete(text, pos);
            return q;
    }

	public SimpleSphereAvecTexture interpreteSimpleSphereAvecTexture()
			throws InterpreteException {
		InterpreteSimpleSphereTexture interpreteH = new InterpreteSimpleSphereTexture();
		interpreteH.setRepertoire(repertoire);
		SimpleSphereAvecTexture t = null;
		try {
			t = (SimpleSphereAvecTexture) interpreteH.interprete(text, pos);
			pos = interpreteH.getPosition();
		} catch (Exception e) {
			throw new InterpreteException(e);

		}
		return t;

	}

	TColor interpreteTColor() {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * @return @throws InterpreteException
	 */
	public Tetraedre interpreteTetraedre() throws InterpreteException {
		InterpreteTetraedre interpreteH = new InterpreteTetraedre();
		interpreteH.setRepertoire(repertoire);
		Tetraedre t = null;
		try {
			t = (Tetraedre) interpreteH.interprete(text, pos);
			pos = interpreteH.getPosition();
		} catch (Exception e) {
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpreteLangage")
							.getString(
									"TETRAEDRE :  ERREUR D'ANALYSE SYNTAXIQUE"),
					e);

		}
		return t;

	}

	public TRI interpreteTriangle() throws InterpreteException {
		InterpreteTriangle interpreteH = new InterpreteTriangle();
		TRI t = null;
		try {
			t = (TRI) interpreteH.interprete(text, pos);
			interpreteH.setRepertoire(repertoire);
			pos = interpreteH.getPosition();
		} catch (Exception e) {
                    Logger.getLogger(InterpreteFacade.class.getName()).log(Level.SEVERE, "", e);
                    
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpreteLangage")
							.getString("ERRUER"));

		}
		return t;
	}

	public TRIObject interpreteTriangles() throws InterpreteException {
		InterpreteListeTriangle interpreteH = new InterpreteListeTriangle();
		interpreteH.setRepertoire(repertoire);
		TRIObject fo = null;
		try {
			fo = (TRIObject) interpreteH.interprete(text, pos);
			pos = interpreteH.getPosition();
		} catch (Exception e) {
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpreteLangage")
							.getString("ERREUR"));

		}
		return fo;
	}

	public TRIEllipsoide interpreteTRIEllipsoide() throws InterpreteException {
		InterpreteTRIEllipsoide interpreteH = new InterpreteTRIEllipsoide();
		interpreteH.setRepertoire(repertoire);
		TRIEllipsoide e = new TRIEllipsoide(new Point3D(0, 0, 0), 1, 2, 3);
		try {
			e = (TRIEllipsoide) interpreteH.interprete(text, pos);
			pos = interpreteH.getPosition();
		} catch (InterpreteException ex) {
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpreteLangage")
							.getString(
									"TRIELLIPSOIDE :  ERREUR D'ANALYSE SYNTAXIQUE "),
					ex);
		}
		return e;
	}

	public TRISphere interpreteTRISphere() throws InterpreteException {
		InterpreteTRISphere interpreteH = new InterpreteTRISphere();
		interpreteH.setRepertoire(repertoire);
		TRISphere s = new TRISphere(new Point3D(0, 0, 0), 1.0);
		try {
			s = (TRISphere) interpreteH.interprete(text, pos);
			pos = interpreteH.getPosition();
		} catch (InterpreteException ex) {
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpreteLangage")
							.getString(
									"TRISPHERE :  ERREUR D'ANALYSE SYNTAXIQUE "),
					ex);
		}
		return s;
	}

	public Tubulaire interpreteTubulaire() throws InterpreteException {
		InterpreteBezier2D interpreteH = new InterpreteBezier2D();
		interpreteH.setRepertoire(repertoire);
		Tubulaire t = null;
		try {
			t = (Tubulaire) interpreteH.interprete(text, pos);
			pos = interpreteH.getPosition();
		} catch (Exception e) {
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpreteLangage")
							.getString("ERRUER"));

		}
		return t;

	}

	public boolean isFailed() {
		return true;
	}

	public boolean isOkay() {
		return okay;
	}

	public void parse(int filetype) {
	}

	@Deprecated
	public String parseEND() {
		InterpretesBase ib = new InterpretesBase();
		ArrayList<Integer> pattern = new ArrayList<Integer>();
		pattern.add(ib.RIGHTPARENTHESIS);
		ib.compile(pattern);
		try {
			ib.read(text, pos);
			pos = ib.getPosition();
		} catch (Exception e) {
			return "";
		}
		return java.util.ResourceBundle.getBundle(
				"info.emptycanvas.library/scripts/InterpreteLangage")
				.getString(")");
	}

	@Deprecated
	public String parseWord() throws InterpreteException {
		InterpreteString is = new InterpreteString();
		String s = "";
		try {
			s = (String) is.interprete(text, pos);
			pos = is.getPosition();
		} catch (Exception e) {
			throw new InterpreteException(
					java.util.ResourceBundle
							.getBundle(
									"info.emptycanvas.library/scripts/InterpreteLangage")
							.getString("ERRUER"));
		}
		return s;
	}

	public void setOkay(boolean okay) {
		this.okay = okay;
	}

	public void setPosition(int pos) {
		this.pos = pos;
	}

    public void setRepertoire(String repertoire) {
		this.repertoire = repertoire;
	}

    public void setText(String text) {
		this.text = text;
	}
        
        
        
}