package com.gl.todo_app.util;

import com.gl.todo_app.exception.ImageNotSupportedException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {

    public static byte[] compression(byte[] image){
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(image);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(image.length);

        byte[] temp = new byte[4 * 1024];

        while(!deflater.finished()){
            int size = deflater.deflate(temp);
            outputStream.write(temp, 0, size);
        }

        try {
            outputStream.close();
        } catch (IOException e) {
            throw new ImageNotSupportedException("Image format not supported.");
        }
        System.out.println("ByteArrayLength:");
        System.out.println(outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    public static byte[] decompression(byte[] image) throws DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(image);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(image.length);

        byte[] temp = new byte[1024 * 4];

        while(!inflater.finished()){
            int size = inflater.inflate(temp);
            outputStream.write(temp, 0, size);
        }

        try {
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return outputStream.toByteArray();
    }

}
