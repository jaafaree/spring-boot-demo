package com.github.jaafar.l.demo.rest;

import com.github.jaafar.l.demo.entity.ReqLog;
import com.github.jaafar.l.common.controller.BaseController;
import com.github.jaafar.l.demo.biz.ReqLogBiz;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/2 18:00
 */
@RestController
@RequestMapping("log")
public class ReqLogController extends BaseController<ReqLogBiz, ReqLog> {
}
