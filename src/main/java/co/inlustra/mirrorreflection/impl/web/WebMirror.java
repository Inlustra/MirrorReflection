/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.inlustra.mirrorreflection.impl.web;

import co.inlustra.mirrorreflection.impl.GsonMirror;
import java.lang.reflect.Field;

/**
 *
 * @author Thomas
 */
public class WebMirror extends GsonMirror {

    @Override
    protected String loadStringField(Field field) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void saveStringField(Field field, String json) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
