package com.github.jaafar.l.demo.ansj;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2017/12/28 11:02
 */
@Slf4j
@AllArgsConstructor
public class AnsjDemo {
    private String input;

    public Result parse(){

        DateTime start = new DateTime();
//        Result r = ToAnalysis.parse(this.input);
        Result r = NlpAnalysis.parse(this.input);
        DateTime end = new DateTime();
        Period p = new Interval(start, end).toPeriod();
        log.info("本次分词处理耗时：" + p.getMillis() + " ms");
        /*KeyWordComputer kwc = new KeyWordComputer(5);
        String title = "";
        String content = "尽管央行新规的出台将限制支付宝等的烧钱补贴，用户享受到的优惠活动可能有所减少，但对于进入一个良性竞争的市场环境而言，消费者享受到优惠之外的，更多是对便捷、安全的考虑。目前，支付宝和微信支付的的市场份额仍有强大的流量优势，但伴随银联能够快速觉醒和其他获得支付牌照的平台逐步入场，像二维码扫码方式又是否会被新技术所取代呢？此次央行新规的出台无疑将促成接下来市场格局的改变。" ;
        Collection<Keyword> result = kwc.computeArticleTfidf(title, content);
        log.info(String.valueOf(result));*/
        return r;
    }


}
