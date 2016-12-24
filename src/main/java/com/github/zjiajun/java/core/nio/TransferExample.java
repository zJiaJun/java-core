package com.github.zjiajun.java.core.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

/**
 * @author zhujiajun
 * @since 2016/12/24
 */
public class TransferExample {

    private final Path pathFrom = Paths.get("/Users/zhujiajun/Documents/temp/test.rar");
    private final Path pathTo = Paths.get("/Users/zhujiajun/Documents/temp/test_copy.rar");


    private void nioTransferFrom() {
        try(FileChannel fileChannelFrom = FileChannel.open(pathFrom, EnumSet.of(StandardOpenOption.READ));
            FileChannel fileChannelTo = FileChannel.open(pathTo,EnumSet.of(StandardOpenOption.CREATE_NEW,StandardOpenOption.WRITE))) {
            long t1 = System.currentTimeMillis();
            fileChannelFrom.transferTo(0,fileChannelFrom.size(),fileChannelTo);
            long t2 = System.currentTimeMillis();
            System.out.println("nio transfer from time: " + (t2 - t1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void bufferReadWrite() {
        try(FileInputStream fileInputStream = new FileInputStream(pathFrom.toFile());
            FileOutputStream fileOutputStream = new FileOutputStream(pathTo.toFile())) {
            long t1 = System.currentTimeMillis();
            byte [] buffer = new byte[1024 * 4];
            int bytes;
            while ((bytes = fileInputStream.read(buffer)) != -1 ) {
                fileOutputStream.write(buffer,0,bytes);
            }
            long t2 = System.currentTimeMillis();
            System.out.println("bufferReadWrite time: " + (t2 - t1));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args)  {
        TransferExample transferExample = new TransferExample();
        transferExample.nioTransferFrom();
        transferExample.bufferReadWrite();
    }


}
