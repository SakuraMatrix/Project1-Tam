package com.github.tamhpn.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufs {
    public static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static Logger logger = LoggerFactory.getLogger(ByteBufs.class);

    public static ByteBuf toByteBuf(Object obj) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            logger.info("Serializing to byte array buffer");
            OBJECT_MAPPER.writeValue(out, obj);
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return Unpooled.buffer().writeBytes(out.toByteArray());
    }
}
