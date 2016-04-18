/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.inlustra.mirrorreflection.impl.android;

import android.content.Context;
import android.content.SharedPreferences;
import co.inlustra.mirrorreflection.impl.GsonMirror;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 *
 * @author Thomas
 */
public class SharedPreferencesMirror extends GsonMirror {

    private SharedPreferences preferences;

    public SharedPreferencesMirror(SharedPreferences preferences, Class<? extends Annotation> annotation) {
        super(annotation);
        this.preferences = preferences;
    }

    public SharedPreferencesMirror(SharedPreferences preferences) {
        this(preferences, null);
    }

    public SharedPreferencesMirror(Context context, String preferencesName, int mode, Class<? extends Annotation> annotation) {
        this(context.getSharedPreferences(preferencesName, mode), annotation);
    }

    public SharedPreferencesMirror(Context context, String preferencesName, Class<? extends Annotation> annotation) {
        this(context, preferencesName, 0, annotation);
    }

    public SharedPreferencesMirror(Context context, String preferencesName, int mode) {
        this(context, preferencesName, mode, null);
    }

    public SharedPreferencesMirror(Context context, String preferencesName) {
        this(context, preferencesName, 0, null);
    }

    @Override
    protected String loadStringField(Field field) {
        return preferences.getString(field.getName(), null);
    }

    @Override
    protected void saveStringField(Field field, String json) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(field.getName(), json);
        editor.apply();
    }

}
