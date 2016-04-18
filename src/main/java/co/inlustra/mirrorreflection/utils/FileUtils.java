/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.inlustra.mirrorreflection.utils;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;

/**
 *
 * @author Thomas
 */
public class FileUtils {

    public static String readFileContent(File file, Charset charset) throws IOException {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "r");
            byte[] buffer = new byte[(int) raf.length()];
            raf.readFully(buffer);
            return new String(buffer, charset);
        } finally {
            closeStream(raf);
        }
    }

    private static void closeStream(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException ex) {
                // do nothing
            }
        }
    }
}
