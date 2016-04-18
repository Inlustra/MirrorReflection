package co.inlustra.mirrorreflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * A simple Field loader, typically used by constructing a ClassMirror instance
 * and then calling the {@link #loadField} instance.
 *
 * @author Thomas
 */
public abstract class ClassMirror {

    /**
     * /**
     * Loads and saves the provided Mirrorable objects' declared fields by
     * declaring a criteria for each field. Excessive loading of Mirrorable
     * objects should be avoided. Private fields can and should be used where
     * necessary.
     *
     * @param <T> A Mirrorable interface marker used as aspect oriented
     * attachment point.
     * @param mirrorable The Mirrorable to be loaded.
     * @return A fully loaded MIrrorable
     * @throws IllegalAccessException
     */
    public <T extends Mirrorable> T loadClass(T mirrorable) throws IllegalAccessException {
        Field[] fields = mirrorable.getClass().getFields();
        for (int i = fields.length - 1; i >= 0; i--) {
            Field field = fields[i];
            if (!isSettable(field)) {
                continue;
            }
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            Object loadedObject = loadField(field);
            setField(field, mirrorable, loadedObject);
        }
        return mirrorable;
    }

    public <T extends Mirrorable> void saveClass(T mirrorable) throws IllegalAccessException {
        Field[] fields = mirrorable.getClass().getFields();
        for (int i = fields.length - 1; i >= 0; i--) {
            Field field = fields[i];
            if (!isSettable(field)) {
                continue;
            }
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            saveField(field, field.get(mirrorable));
        }
    }

    /**
     * Performs the set of the object to the field.
     *
     * @param field Field to be set.
     * @param parent Parent of the Object.
     * @param loadedObject Loaded object defined by its subclass
     * @throws IllegalAccessException
     */
    protected void setField(Field field, Mirrorable parent, Object loadedObject)
            throws IllegalAccessException {
        if (loadedObject != null) {
            field.set(parent, loadedObject);
        }
    }

    /**
     * Determines whether or not the Field should be loaded from the abstract
     * method {@link #loadField}. The default implementation of this class will
     * ignore transient and final fields but still loads static fields.
     *
     * @param field
     * @return Whether or not the Field can be set.
     */
    protected boolean isSettable(Field field) {
        int modifiers = field.getModifiers();
        return !Modifier.isTransient(modifiers)
                && !Modifier.isFinal(modifiers);
    }

    /**
     * Will set the Field to the Object returned by this method.
     *
     * @param field The field that shall be set by the value.
     * @return The Object associated with said field.
     */
    protected abstract Object loadField(Field field);

    /**
     * Will mirror the associated field.
     *
     * @param field The field to be mirrored.
     * @param obj The value of the field to be mirrored.
     */
    protected abstract void saveField(Field field, Object obj);

}
