package com.ro.controller.reader;

import com.ro.controller.tasks.FromES;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class MessageReader {
    private static final Logger logger = LogManager.getLogger(MessageReader.class);

    public String readMessage() {
        byte[] len = new byte[4];
        BufferedInputStream inputStream = new BufferedInputStream(System.in);
        try {
            if(inputStream.read(len) == 4) {
                int length = ByteBuffer.wrap(len).getInt();
                logger.info("Received length: " + length);
                if(length == 0) {
                    logger.info("Message length is 0. Systems exit");
                    System.exit(0);
                } else {
                    byte[] message = new byte[length];
                    if(inputStream.read(message) > 0)
                        return new String(message, StandardCharsets.UTF_8);
                }
            } else {
                throw new Exception("Error on reading message length");
            }

        } catch (IOException ioe) {
          logger.error(ioe.getMessage(), ioe);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        return null;
    }
}