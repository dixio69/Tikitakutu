package com.hehe.tikitakutu.util;

import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpSession;

@Log4j2
public class SessionLogger {
    public static void logSession(HttpSession session){
//        log.info("Receive request | path [" + session.getServletContext().getContextPath() + "] | session id = " + session.getId());
    }
}
