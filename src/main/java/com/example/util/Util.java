package com.example.util;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Util {
  private static boolean externalGoldfile = false;

  public static byte[] readFileOrResource(Class c, String path) throws IOException {
    byte[] result = null;
    if (externalGoldfile) {
      FileInputStream fin = new FileInputStream(new File(path));
      result = IOUtils.toByteArray(fin);
      fin.close();
    } else {
      InputStream in = c.getResourceAsStream(path);
      result = IOUtils.toByteArray(in);
      in.close();
    }
    return result;
  }

  public static void setExternalGoldfile() {
    externalGoldfile = true;
  }
}
