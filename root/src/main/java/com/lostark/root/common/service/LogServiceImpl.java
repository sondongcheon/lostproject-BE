package com.lostark.root.common.service;

import com.lostark.root.common.db.entity.LogCountEntity;
import com.lostark.root.common.db.repository.LogCountRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogServiceImpl implements LogService {

    private final LogCountRepository logCountRepository;

    @Override
    public long[] getLog(long id) {
        //id 고정
        List<LogCountEntity> log = logCountRepository.findAllById(Arrays.asList(id, id+1));
        return new long[] {log.get(0).getCount(), log.get(1).getCount()};
    }

    @Override
    public void setMainLog(Cookie[] cookies, HttpServletResponse response) {
        if(cookies != null ) {
            for (Cookie value : cookies) {
                if (value.getName().equals("MCP")) return;
            }
        }

        Cookie cookie = new Cookie("MCP", null);
        cookie.setMaxAge(24*60*60);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        response.addCookie(cookie);

        logCountRepository.incrementCountByName("totalPage");
        logCountRepository.incrementCountByName("todayPage");


    }
}
