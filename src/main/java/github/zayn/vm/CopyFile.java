package github.zayn.vm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

public class CopyFile {
    public void copyFileByStream(File source, File target) throws IOException {
        InputStream is = new FileInputStream(source);
        OutputStream os = new FileOutputStream(target);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
    }

    public void copyFileByChannel(File source, File target) throws IOException {
        FileChannel sourceChannel = new FileInputStream(source).getChannel();
        FileChannel targetChannel = new FileOutputStream(target).getChannel();
        long count = sourceChannel.size();
        while (count > 0) {
            long transferred = sourceChannel.transferTo(sourceChannel.position(), count, targetChannel);
            sourceChannel.position(sourceChannel.position() + transferred);
            count -= transferred;
        }
    }

    public static void main(String[] args) throws IOException {
        CopyFile copyFile = new CopyFile();
        File source = new File("/Users/zhangjiayi/Downloads/updatePids.csv");
        File target = new File("/Users/zhangjiayi/Downloads/new.csv");
        copyFile.copyFileByStream(source, target);
    }
}
