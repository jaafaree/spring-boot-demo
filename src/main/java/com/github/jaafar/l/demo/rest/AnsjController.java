package com.github.jaafar.l.demo.rest;

import com.github.jaafar.l.common.VO.ObjectRestResponse;
import com.github.jaafar.l.demo.ansj.AnsjDemo;
import org.ansj.domain.Result;
import org.springframework.web.bind.annotation.*;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2017/12/28 11:13
 */
@RestController
@RequestMapping("ansj")
public class AnsjController {
    @RequestMapping(method = RequestMethod.POST)
    public ObjectRestResponse doAnsj(@RequestParam String input){
        AnsjDemo ad = new AnsjDemo(input);
        Result r = ad.parse();
        return new ObjectRestResponse().rel(true).data(r.toString());
    }
}
