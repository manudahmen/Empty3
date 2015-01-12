/***
Global license : 

    Microsoft Public Licence
    
    author Manuel Dahmen <ibiiztera.it@gmail.com>

***/


package info.emptycanvas.library.testing;

import java.util.ArrayList;


public class TestInstanceImpl extends TestInstance {

    
    
    
    @Override
    public Parameter getDynParameter(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Parameter> getDynParameters() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Parameter> getInitParameters() {
        ArrayList<Parameter> params = test.getInitParams();
        return null;
        
    }

    @Override
    public boolean newInstance(ArrayList<Parameter> parameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean setDynParameter(Parameter parameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
