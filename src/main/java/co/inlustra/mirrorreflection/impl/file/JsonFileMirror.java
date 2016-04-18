/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.inlustra.mirrorreflection.impl.file;

import co.inlustra.mirrorreflection.Mirrorable;
import co.inlustra.mirrorreflection.impl.GsonMirror;
import co.inlustra.mirrorreflection.utils.FileUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomas
 */
public class JsonFileMirror extends GsonMirror {

    private final File file;
    private JsonObject json;
    private final static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public JsonFileMirror(File file, Gson gson, Class<? extends Annotation> annotation) {
        super(gson, annotation);
        this.file = file;
    }

    public JsonFileMirror(File file, Class<? extends Annotation> annotation) {
        super(annotation);
        this.file = file;
    }

    public JsonFileMirror(File file) {
        this.file = file;
    }

    @Override
    public <T extends Mirrorable> void preLoad(T mirrorable) {
        if (!file.exists()) {
            return;
        }
        try {
            String content = FileUtils.readFileContent(file, Charset.forName("UTF-8"));
            json = new Gson().fromJson(content, JsonObject.class);
        } catch (IOException ex) {
            Logger.getLogger(JsonFileMirror.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected String loadStringField(Field field) {
        return json.get(field.getName()).getAsString();
    }

    @Override
    protected void saveStringField(Field field, String json) {
        this.json.addProperty(field.getName(), json);
    }

    @Override
    public <T extends Mirrorable> void preSave(T mirrorable) {
        this.json = new JsonObject();
    }

    @Override
    public <T extends Mirrorable> void postSave(T mirrorable) {
        if (!file.exists()) {
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), "UTF-8"))) {
            writer.write(gson.toJson(json));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JsonFileMirror.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(JsonFileMirror.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JsonFileMirror.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
