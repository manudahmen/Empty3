package info.emptycanvas.library.Engine;

import info.emptycanvas.library.object.Representable;
import java.lang.reflect.Method;

/**
 *
 * @author Manuel Dahmen <manuel.dahmen@gmail.com>
 */
public class RegisterObject {
    public class Entry
    {
        private Class<Representable> class3d;
        private boolean positionnable;
        private boolean rotatable;
        private boolean moveable;
        private boolean sizable;
        private Method positionnableSpecialMethod;
        private Method rotatableSpecialMethod;
        private Method moveableSpecialMethod;
        private Method sizableSpecialMethod;

        public Entry(Class<Representable> class3d, boolean positionnable, boolean rotatable, boolean moveable, boolean sizable, Method positionnableSpecialMethod, Method rotatableSpecialMethod, Method moveableSpecialMethod, Method sizableSpecialMethod) {
            this.class3d = class3d;
            this.positionnable = positionnable;
            this.rotatable = rotatable;
            this.moveable = moveable;
            this.sizable = sizable;
            this.positionnableSpecialMethod = positionnableSpecialMethod;
            this.rotatableSpecialMethod = rotatableSpecialMethod;
            this.moveableSpecialMethod = moveableSpecialMethod;
            this.sizableSpecialMethod = sizableSpecialMethod;
        }


        public Class<Representable> getClass3d() {
            return class3d;
        }

        public boolean isPositionnable() {
            return positionnable;
        }

        public boolean isRotatable() {
            return rotatable;
        }

        public boolean isMoveable() {
            return moveable;
        }

        public Method getPositionnableSpecialMethod() {
            return positionnableSpecialMethod;
        }

        public Method getRotatableSpecialMethod() {
            return rotatableSpecialMethod;
        }

        public Method getMoveableSpecialMethod() {
            return moveableSpecialMethod;
        }

        public Method getSizableSpecialMethod() {
            return sizableSpecialMethod;
        }
        
        
        
    }

    
}
