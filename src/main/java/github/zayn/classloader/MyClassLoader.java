package github.zayn.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * @ClassName MyClassLoader
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2023/10/8 8:12 PM
 **/
public class MyClassLoader extends ClassLoader {
    //定义java文件根路径
    private String javaRootDir;
    //定义class文件根路径
    private String classRootDir;
    // 缓存已经加载的类 
    private HashMap<String, Class<?>> loadedClasses;

    public MyClassLoader(String javaRootDir, String classRootDir) {
        this.javaRootDir = javaRootDir;
        this.classRootDir = classRootDir;
        loadedClasses = new HashMap<>();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> clazz = null;
        String fileStub = name.replace('.', '/');
        String javaFileName = javaRootDir + fileStub + ".java";
        String classFileName = classRootDir + fileStub + ".class";
        File javaFile = new File(javaFileName);
        File classFile = new File(classFileName);
        // 当指定Java源文件存在，且Class文件不存在，或者Java源文件的修改时间比Class文件的修改时间更晚时，重新编译
        if (javaFile.exists() && (!classFile.exists() || javaFile.lastModified() > classFile.lastModified())) {
            try {
                // 如果编译失败，或者该Class文件不存在
                boolean compile = compile(javaFileName, classRootDir);
                if (!compile || !classFile.exists()) {
                    throw new ClassNotFoundException();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 如果class文件存在，系统负责将该文件转成Class对象
        if (classFile.exists()) {
            try {
                // 将Class文件的二进制数据读入数组
                byte[] raw = getBytes(classFileName);
                // 调用ClassLoader的defineClass方法将二进制数据转成Class对象
                clazz = defineClass(name, raw, 0, raw.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 如果clazz为null,表明加载失败，则抛出异常
        if (clazz == null) {
            throw new ClassNotFoundException(name);
        }
        return clazz;
    }

    /**
     * 读取一个文件的内容
     */
    private byte[] getBytes(String fileName) throws IOException {

        File file = new File(fileName);
        long len = file.length();
        byte[] raw = new byte[(int) len];
        try (
                FileInputStream fin = new FileInputStream(file)
        ) {
            // 一次性读取Class文件的全部二进制数据
            int r = fin.read(raw);
            if (r != len) {
                throw new IOException("无法读取全部文件： " + r + " != " + len);
            }
            return raw;
        }

    }

    /**
     * 定义编译制定Java文件的方法
     *
     * @param javaFile 类全限定名
     * @param classFile .class全限定名           
     * @return
     */
    private boolean compile(String javaFile, String classFile) throws IOException {
        System.out.println("CompileClassLoader 正在编译 " + javaFile + "....");
        // 调用系统javac 的命令
        Process p = Runtime.getRuntime().exec("javac  -source 1.8 -target 1.8 -d " + classFile + " " + javaFile);
        try {
            // 其他线程都在等待这个线程完成
            p.waitFor();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        // 获取javac线程的退出值
        int ret = p.exitValue();
        // 返回编译是否成功
        return ret == 0;
    }


    public static void main(String[] args) {
        MyClassLoader classLoader = new MyClassLoader(
                "/Users/zhangjiayi/IdeaProjects/java-playground/src/main/java/",
                "/Users/zhangjiayi/IdeaProjects/java-playground/target/classes/");
        try {
            Class<?> productInfo = classLoader.loadClass("github.zayn.annotation.ProductInfoV2");
            System.out.println("Class loaded successfully: " + productInfo.getName());
            System.out.println("Class loader is " + productInfo.getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}