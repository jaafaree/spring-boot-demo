package com.github.jaafar.l.demo.biz;

import com.github.jaafar.l.common.biz.BaseBiz;
import com.github.jaafar.l.demo.entity.ReqLog;
import com.github.jaafar.l.demo.mapper.ReqLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/2 14:29
 */
@Service
@Transactional
public class ReqLogBiz extends BaseBiz<ReqLogMapper, ReqLog> {
}
