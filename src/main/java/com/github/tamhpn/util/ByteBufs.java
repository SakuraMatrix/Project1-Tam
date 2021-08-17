package com.github.tamhpn.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufs {
    public static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    
    public static ByteBuf toByteBuf(Object obj) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            OBJECT_MAPPER.writeValue(out, obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Unpooled.buffer().writeBytes(out.toByteArray());
    }
}
