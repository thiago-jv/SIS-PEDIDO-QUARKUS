package org.cliente.utils;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static String readFileData(String file) throws IOException {

        InputStream is = new FileInputStream("src/test/resources/" + file);
        String jsonTxt = IOUtils.toString(is, "UTF-8");

        return jsonTxt;
    }
}
