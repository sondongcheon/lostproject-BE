package com.lostark.root.board.service;

import com.lostark.root.board.db.entity.NoticeEntity;
import com.lostark.root.board.db.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public String test() {
        Optional<NoticeEntity> noticeEntity = noticeRepository.findById((long)1);
        return noticeEntity.get().getContent();
    }
}
