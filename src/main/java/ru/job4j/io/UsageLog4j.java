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
        byte a = 3;
        short b = 23;
        int c = 33;
        long d = 47L;
        float e = 47.21F;
        double f = 47.00;
        char g = 'a';
        boolean h = true;

        LOG.debug("Name variable: a, value : {}", a);
        LOG.debug("Name variable: b, value : {}", b);
        LOG.debug("Name variable: c, value : {}", c);
        LOG.debug("Name variable: d, value : {}", d);
        LOG.debug("Name variable: e, value : {}", e);
        LOG.debug("Name variable: f, value : {}", f);
        LOG.debug("Name variable: g, value : {}", g);
        LOG.debug("Name variable: h, value : {}", h);

        try {
            throw new Exception("Not supported code");
        } catch (Exception k) {
            LOG.error("Exception in log example", k);
        }
    }
}