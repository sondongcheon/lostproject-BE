package com.lostark.root.board.service;

import com.lostark.root.board.db.dto.req.MiniWriteReq;
import com.lostark.root.board.db.dto.req.NoticeWriteReq;
import com.lostark.root.board.db.dto.res.MiniMainRes;
import com.lostark.root.board.db.dto.res.NoticeContentRes;
import com.lostark.root.board.db.dto.res.NoticeMainRes;
import com.lostark.root.board.db.entity.MiniEntity;
import com.lostark.root.board.db.entity.NoticeEntity;
import com.lostark.root.board.db.repository.NoticeCommentRepository;
import com.lostark.root.board.db.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeCommentRepository noticeCommentRepository;


    @Override
    public String test() {
        Optional<NoticeEntity> noticeEntity = noticeRepository.findById((long)1);
        return noticeEntity.get().getContent();
    }

    @Override
    public NoticeMainRes getNoticeMain(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC,"createAt"));

        Page<NoticeEntity> noticeEntityPage = noticeRepository.findAll(pageable);


        return NoticeMainRes.builder()
                .page(noticeEntityPage.getTotalPages())
                .contentList(noticeEntityPage
                        .stream()
                        .map(notice -> NoticeMainRes.ContentList.fromEntity(
                                notice,
                                noticeCommentRepository.countByNotice_NoticeId(notice.getNoticeId())
                        ))
                        .toList())
                .build();

    }

    @Override
    public NoticeContentRes getNoticeContent(long id) {
        Optional<NoticeEntity> optionalNotice = noticeRepository.findById(id);
        if (optionalNotice.isPresent()) {
            NoticeEntity noticeEntity = optionalNotice.get();
            return NoticeContentRes.fromEntity(noticeEntity);
        }
        return null;
    }

    @Override
    public long setNoticeContent(NoticeWriteReq noticeWriteReq) {
        if(noticeWriteReq.getPassword() == 1136) {
            return noticeRepository.save(NoticeEntity.fromReq(noticeWriteReq)).getNoticeId();
        } else {
            return 0L;
        }
    }


}
