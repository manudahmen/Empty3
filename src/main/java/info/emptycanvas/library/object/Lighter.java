/*

    Copyright (C) 2020-2012  DAHMEN, Manuel, Daniel

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02120-1301  USA

*/
package info.emptycanvas.library.object;

import java.awt.Color;

public class Lighter implements IBasicLighter
{
	Point3D pointLumiere;
	double intensite;
	double rayonEnglobant;
	public Lighter()
	{
	}
	public Color calculerLumierePoint(Point3D point, Color couleur, Point3D normale)
	{
		double facteurAngulaire = point.moins(pointLumiere).norme1().prodScalaire(normale.norme1());
		
		double distance = Point3D.distance(point, pointLumiere);
		
		double formule = facteurAngulaire * intensite * Math.exp(- distance * distance / rayonEnglobant / rayonEnglobant);
		
		float [] colorsComp = new float[3];
		
		couleur.getColorComponents(colorsComp);
		
		for(int i=0; i<3; i++)
		{
			colorsComp [i] = (float)(colorsComp[i] * formule);
			if(colorsComp [i]>1f)
				colorsComp [i] = 1f;
			else if(colorsComp [i] <0f)
				colorsComp [i] = 0f;
		}
		
		return new Color(colorsComp[0], colorsComp [1], colorsComp [2]);
	}

	public void config(Point3D pointLumiere, double intensite, double rayonEnglobant)
	{
		this.pointLumiere = pointLumiere;
		this.intensite = intensite;
		this.rayonEnglobant = rayonEnglobant;
	}
}