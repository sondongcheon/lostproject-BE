package com.lostark.root.board.service;

import com.lostark.root.board.db.dto.req.MiniWriteReq;
import com.lostark.root.board.db.dto.res.MiniMainRes;
import com.lostark.root.board.db.entity.MiniEntity;
import com.lostark.root.board.db.repository.MiniRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MiniServiceImpl implements MiniService{

    private final MiniRepository miniRepository;


    @Override
    public List<MiniMainRes> getMiniMain() {
        List<MiniEntity> miniMainList = miniRepository.findAll(Sort.by(Sort.Direction.DESC,"createAt"));
        return miniMainList.stream()
                .map(MiniMainRes::fromEntity)
                .toList();
    }

    @Override
    public void setMiniContent(MiniWriteReq miniContent) {
        miniRepository.save(MiniEntity.fromReq(miniContent));
    }
}
