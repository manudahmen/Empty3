/*

    Vous êtes libre de :

*/

package info.emptycanvas.library.tribase;

import info.emptycanvas.library.object.Point2D;

	public class SurfaceCirculaire implements Surface {
		private int max;
		private double radius;

		public SurfaceCirculaire(double r) {
			this.radius = r;
		}

		@Override
		public Point2D getPoint(int i) {
			return new Point2D(0.5 * Math.cos(Math.PI * 2 * i / max),
					0.5 * Math.sin(Math.PI * 2 * i / max));
		}

		@Override
		public void setMax(int n) {
			max = n;
		}

		
	}