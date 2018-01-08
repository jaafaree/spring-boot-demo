package com.github.jaafar.l.demo.biz;

import com.github.jaafar.l.common.biz.BaseBiz;
import com.github.jaafar.l.demo.entity.CnUser;
import com.github.jaafar.l.demo.mapper.CnUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/3 10:49
 */
@Service
@Transactional
public class CnUserBiz extends BaseBiz<CnUserMapper, CnUser> {

    public CnUser getCnUserByUsername(String username){
        CnUser cnUser = new CnUser();
        cnUser.setUsername(username);
        CnUser rtnUser = mapper.selectOne(cnUser);
        return rtnUser;
    }

    public boolean getUserExistByUsername(String username){
        return mapper.getUserExistByUsername(username) > 0;
    }


}
