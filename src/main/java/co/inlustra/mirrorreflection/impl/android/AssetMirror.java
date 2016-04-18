/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.inlustra.mirrorreflection.impl.android;

import android.content.Context;
import co.inlustra.mirrorreflection.impl.util.PropertiesMirror;
import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 *
 * @author Thomas
 */
public class AssetMirror extends PropertiesMirror {

    public AssetMirror(Context context, String assetName, Class<? extends Annotation> annotation) throws IOException {
        super(context.getAssets().open(assetName), annotation);
    }

    public AssetMirror(Context context, String assetName) throws IOException {
        super(context.getAssets().open(assetName));
    }

}
