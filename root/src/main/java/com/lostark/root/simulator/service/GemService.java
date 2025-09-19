package com.lostark.root.simulator.service;

import com.lostark.root.simulator.db.dto.req.GemProcessReq;
import com.lostark.root.simulator.db.dto.res.GemBasicRes;

import java.util.Optional;

public interface GemService {

    public GemBasicRes getBasicInfo(GemProcessReq gemProcessReq, int type, int grade);
    public GemBasicRes processGem(GemProcessReq gemProcessReq, int type);
    public GemBasicRes reRollChoiceList(GemProcessReq gemProcessReq);
}
