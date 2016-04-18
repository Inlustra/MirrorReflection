/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.inlustra.mirrorreflection;

import co.inlustra.mirrorreflection.ClassMirror;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Can require a field to have the required annotation to be loaded when called
 * by a subclass.
 *
 * @author Thomas
 */
public abstract class AnnotatedMirror extends FinalizableClassMirror {

    private final Class<? extends Annotation> annotation;

    /**
     * Provides the annotation to the class.
     *
     * @param annotation The Annotation to be required.
     */
    public AnnotatedMirror(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    public AnnotatedMirror() {
        this(null);
    }

    /**
     * With no annotation set, the field is loaded only if it is not transient
     * or final.
     *
     * @param field
     * @return
     */
    @Override
    protected boolean isSettable(Field field) {
        return (annotation == null
                ? super.isSettable(field)
                : field.isAnnotationPresent(annotation));
    }

}
