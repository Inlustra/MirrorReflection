/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.inlustra.mirrorreflecion;

import co.inlustra.mirrorreflection.Mirrorable;
import co.inlustra.mirrorreflection.impl.file.JsonFileMirror;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
public class JsonFileLoad {

    private static File file;

    public JsonFileLoad() {
    }

    @BeforeClass
    public static void setUpClass() {
        file = new File(System.getProperty("user.home"), "test.json");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(JsonFileLoad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
    public void save() {
        TestProperties props = new TestProperties();
        for (int i = 0; i < 50; i++) {
            props.list.add("This number is:" + i);
        }
        try {
            new JsonFileMirror(file).saveClass(props);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JsonFileLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void load() {
        TestProperties props = new TestProperties();
        try {
            new JsonFileMirror(file).loadClass(props);
            for(int i = 0;i<50;i++){
                assertEquals(props.list.get(i), "This number is:" + i);
            }
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JsonFileLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static class TestProperties implements Mirrorable {

        public int myField = 50;
        public String mySecondField = "Small Test";
        public String[] array = new String[]{"Test", "Test", "Test",
            "Test", "Test", "Test", "Test", "Test",
            "Test", "Test", "Test", "Test", "Test", "Test"};
        public List<String> list = new ArrayList();
    }
}
