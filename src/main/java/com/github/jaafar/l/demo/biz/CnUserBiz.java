package com.github.jaafar.l.demo.biz;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
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

    @Cache(key = "users{1}", expireExpression = "{2}")
    public CnUser getCnUserByUsername(String username, int expireTime){
        CnUser cnUser = new CnUser();
        cnUser.setUsername(username);
        CnUser rtnUser = mapper.selectOne(cnUser);
        return rtnUser;
    }

    public boolean getUserExistByUsername(String username){
        return mapper.getUserExistByUsername(username) > 0;
    }

    @Cache(key = "users{1}", expireExpression = "{2}")
    public CnUser insertUser(String username, int expireTime, CnUser cnUser){
        super.insert(cnUser);
        return super.selectOne(cnUser);
    }

    @CacheClear(key = "users{1.username}")
    public void updateUser(CnUser cnUser){
        super.updateSelectiveById(cnUser);
    }
}
