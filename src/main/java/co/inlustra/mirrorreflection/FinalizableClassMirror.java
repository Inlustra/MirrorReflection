/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.inlustra.mirrorreflection;

import java.lang.reflect.Field;

/**
 *
 * @author Thomas
 */
public abstract class FinalizableClassMirror extends ClassMirror {

    @Override
    public <T extends Mirrorable> void saveClass(T mirrorable) throws IllegalAccessException {
        this.preSave(mirrorable);
        super.saveClass(mirrorable);
        this.postSave(mirrorable);
    }

    @Override
    public <T extends Mirrorable> T loadClass(T mirrorable) throws IllegalAccessException {
        this.preLoad(mirrorable);
        T returnMirror = super.loadClass(mirrorable);
        this.postLoad(mirrorable);
        return returnMirror;
    }

    protected abstract <T extends Mirrorable> void preSave(T mirrorable);

    protected abstract <T extends Mirrorable> void postSave(T mirrorable);

    protected abstract <T extends Mirrorable> void preLoad(T mirrorable);

    protected abstract <T extends Mirrorable> void postLoad(T mirrorable);

}
