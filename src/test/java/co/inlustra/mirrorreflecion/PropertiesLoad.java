/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.inlustra.mirrorreflecion;

import co.inlustra.mirrorreflection.ClassMirror;
import co.inlustra.mirrorreflection.Mirrorable;
import co.inlustra.mirrorreflection.impl.GsonMirror;
import co.inlustra.mirrorreflection.impl.util.PropertiesMirror;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Thomas
 */
public class PropertiesLoad {

    private static File file;
    private static String second = "JustATest";
    private static String first = "5";

    public PropertiesLoad() {
    }

    @BeforeClass
    public static void setUpClass() {
        try {
            file = File.createTempFile("PropertiesLoad_JUnit", ".properties");
            Properties properties = new Properties();
            properties.put("myField", first);
            properties.put("mySecondField", second);
            properties.store(new FileOutputStream(file), "");
        } catch (IOException ex) {
            Logger.getLogger(PropertiesLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterClass
    public static void tearDownClass() {
        file.deleteOnExit();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void saveFromLoadInto() {
        int testInt = 9028;
        String testString = "Just a test.";
        try {
            Properties propsFile = new Properties();
            TestProperties properties = new TestProperties();
            properties.myField = testInt;
            properties.mySecondField = testString;
            ClassMirror mirror = new PropertiesMirror(propsFile);
            mirror.saveClass(properties);
            
            properties = new TestProperties();
            mirror.loadClass(properties);
            System.out.println(properties.myField);
            System.out.println(properties.mySecondField);
            assertEquals(testInt, properties.myField);
            assertEquals(testString, properties.mySecondField);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PropertiesLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void LoadInto() {
        TestProperties props = new TestProperties();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(file));
            assertEquals(first, properties.get("myField"));
            assertEquals(second, properties.get("mySecondField"));
            new PropertiesMirror(properties).loadClass(props);
        } catch (IOException ex) {
            Logger.getLogger(PropertiesLoad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PropertiesLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(5, props.myField);
        assertEquals("JustATest", props.mySecondField);
    }

    public static class TestProperties implements Mirrorable {

        public int myField;
        public String mySecondField;
    }
}
