/*

    Vous êtes libre de :

*/
package info.emptycanvas.library.object;

import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 *
 * @author Manuel DAHMEN
 */
public interface Serialisable {
    public Serialisable decode(DataInputStream in);
    public int encode(DataOutputStream out);
    public int type();
}
