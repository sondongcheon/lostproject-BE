package com.lostark.root.common.service;

import com.lostark.root.common.db.entity.LogCountEntity;
import com.lostark.root.common.db.repository.LogCountRepository;
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
}
