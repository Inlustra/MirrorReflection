package co.inlustra.mirrorreflection.impl;

import co.inlustra.mirrorreflection.AnnotatedMirror;
import co.inlustra.mirrorreflection.Mirrorable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 *
 * @author Thomas
 */
public abstract class GsonMirror extends AnnotatedMirror {

    private final Gson gson;

    public GsonMirror(Gson gson, Class<? extends Annotation> annotation) {
        super(annotation);
        this.gson = gson;
    }

    public GsonMirror(Class<? extends Annotation> annotation) {
        this(new Gson(), annotation);
    }

    public GsonMirror() {
        this.gson = new Gson();
    }

    @Override
    protected Object loadField(Field field) {
        return gson.fromJson(loadStringField(field),
                TypeToken.get(field.getGenericType()).getType());
    }

    protected abstract String loadStringField(Field field);

    @Override
    protected void saveField(Field field, Object obj) {
        saveStringField(field, gson.toJson(obj));
    }

    protected abstract void saveStringField(Field field, String json);

    @Override
    public <T extends Mirrorable> void postLoad(T mirrorable) {
    }

    @Override
    public <T extends Mirrorable> void postSave(T mirrorable) {
    }

    @Override
    public <T extends Mirrorable> void preLoad(T mirrorable) {
    }

    @Override
    public <T extends Mirrorable> void preSave(T mirrorable) {
    }

}
