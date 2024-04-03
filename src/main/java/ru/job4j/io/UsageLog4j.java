package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        String name = "Evgeny Maslakov";
        int age = 32;
        LOG.debug("User info name : {}, age : {}", name, age);
        LOG.debug("byte : {}, short : {}, int : {}, long : {}, float : {}, double : {}, boolean : {}, char : {}",
                127, 324, 555L, 5.5, 6.6F, 7.7D, true, 'c');
        try {
            throw new Exception("Not supported code");
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
    }
}
