/*

    Vous êtes libre de :

*/

package be.ibiiztera.md.pmatrix.pushmatrix.structures;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 15 oct. 2011
 *
 */
public interface Group {
	public void addGroup(Group g);
	public void delGroup(Group g);
	public Group groups();
}
