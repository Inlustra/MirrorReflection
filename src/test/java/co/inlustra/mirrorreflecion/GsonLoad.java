/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.inlustra.mirrorreflecion;

import co.inlustra.mirrorreflection.Mirrorable;
import co.inlustra.mirrorreflection.impl.GsonMirror;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thomas
 */
public class GsonLoad {

    public GsonLoad() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void gsonLoadTest() {
        try {
            TestProperties props = new TestProperties();
            final Map<String, String> gsonList = new HashMap<>();
            gsonList.put("myField", "50");
            gsonList.put("myField2", "Just a test");
            GsonMirror mirror = new GsonMirror() {

                @Override
                protected String loadStringField(Field field) {
                    System.out.println("Loading field name: " + field.getName());
                    return gsonList.get(field.getName());
                }

                @Override
                protected void saveStringField(Field field, String json) {
                    System.out.println("Saving field name: " + field.getName());
                    gsonList.put(field.getName(), json);
                }
            };
            mirror.loadClass(props);
            assertEquals(gsonList.get("myField"), String.valueOf(props.myField));
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PropertiesLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static class TestProperties implements Mirrorable {

        public int myField;
        public String mySecondField;
    }

}
