package github.zayn.vmtraining;

import java.io.IOException;
import java.io.InputStream;

public class ObjectTraining {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myLoader1 = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String filename = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream is = getClass().getResourceAsStream(filename);
                if (is == null) {
                    return super.loadClass(name);
                }
                byte[] b = new byte[0];
                try {
                    b = new byte[is.available()];
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    is.read(b);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return defineClass(name, b, 0, b.length);
            }
        };
        ClassLoader myLoader2 = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String filename = name.substring(name.lastIndexOf(".") + 1) + ".class";

                InputStream is = getClass().getResourceAsStream(filename);
                if (is == null) {
                    return super.loadClass(name);
                }
                byte[] b = new byte[0];
                try {
                    b = new byte[is.available()];
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    is.read(b);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return defineClass(name, b, 0, b.length);
            }
        };
        Object objectTraining = myLoader1.loadClass("github.zayn.vmtraining.ObjectTraining").newInstance();
        Object objectTraining2 = myLoader2.loadClass("github.zayn.vmtraining.ObjectTraining").newInstance();
        System.out.println(objectTraining.getClass().getClassLoader());
        System.out.println(objectTraining2.getClass().getClassLoader());

        System.out.println(objectTraining.equals(objectTraining2));
    }
}
