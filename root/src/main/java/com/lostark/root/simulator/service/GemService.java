package com.lostark.root.simulator.service;

import com.lostark.root.simulator.db.dto.GemEnums.EffectEnum;
import com.lostark.root.simulator.db.dto.GemStateDto;
import com.lostark.root.simulator.db.dto.req.GemProcessReq;
import com.lostark.root.simulator.db.dto.res.GemBasicRes;

import java.util.Optional;

public interface GemService {

    public GemBasicRes getBasicInfo(GemProcessReq gemProcessReq, int type);
    public Optional<EffectEnum> processResult(GemProcessReq gemProcessReq);
}
