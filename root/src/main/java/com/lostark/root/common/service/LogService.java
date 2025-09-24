package com.lostark.root.common.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public interface LogService {

    public long[] getLog(long id);
    public void setMainLog(Cookie[] cookies, HttpServletResponse response);
}
