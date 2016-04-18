/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.inlustra.mirrorreflection.impl.util;

import co.inlustra.mirrorreflection.Mirrorable;
import co.inlustra.mirrorreflection.impl.GsonMirror;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 *
 * @author Thomas
 */
public class PropertiesMirror extends GsonMirror {

    private final Properties properties;

    public PropertiesMirror(Properties props, Class<? extends Annotation> annotation) {
        super(annotation);
        this.properties = props;
    }

    public PropertiesMirror(Properties props) {
        this(props, null);
    }

    public PropertiesMirror(InputStream stream, Class<? extends Annotation> annotation) throws IOException {
        super(annotation);
        this.properties = new Properties();
        this.properties.load(stream);
    }

    public PropertiesMirror(InputStream stream) throws IOException {
        this(stream, null);
    }

    @Override
    protected String loadStringField(Field field) {
        return properties.getProperty(field.getName());
    }

    @Override
    protected void saveStringField(Field field, String json) {
        this.properties.setProperty(field.getName(), json);
    }

    public <T extends Mirrorable> void saveProperties(T mirrorable,
            OutputStream stream, String description) throws IOException, IllegalAccessException {
        this.saveClass(mirrorable);
        this.properties.store(stream, description);
    }

    public <T extends Mirrorable> void saveProperties(T mirrorable, OutputStream stream)
            throws IOException, IllegalAccessException {
        this.saveProperties(mirrorable, stream, "");
    }

}
