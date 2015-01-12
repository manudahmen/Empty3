/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

public class TRIGeneratorUtil {
	public static TRIObject P32DTriQuad(Point3D[] points, int dimx, int dimy) {
		TRIObject tri = new TRIObject();
		
		for (int i = 0; i < dimx - 1; i++)
			for (int j = 0; j < dimy - 1; j++) {

				TRI t1 = new TRI(points[dimx * j + i], points[dimx * (j + 1)
						+ i], points[dimx * (j + 1) + (i + 1)], points[dimx * j
						+ i].texture());
				tri.add(t1);
				
				TRI t2 = new TRI(points[dimx * j + i], points[dimx * j
						+ (i + 1)], points[dimx * (j + 1) + (i + 1)],
						points[dimx * j + i].texture());
				tri.add(t2);

			}

		return tri;

	}

	public static TRIObject P32DTriQuad(Point3D[][] controle, int dimx, int dimy) {
		Point3D [] bis = new Point3D[dimx*dimy];
 		for(int i=0; i<dimx;i++)
			for(int j=0; j<dimy;j++)
			{
				bis[j*dimx+i] = controle[i][j];
			}
		return P32DTriQuad(bis, dimx, dimy);
	}

}
