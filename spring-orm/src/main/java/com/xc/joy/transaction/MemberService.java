package com.xc.joy.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lxcecho 909231497@qq.com
 * @since 22:38 08-07-2022
 */
@Service
public class MemberService {
    @Autowired
    private MemberDao memberDao;


    public List<Member> queryAll() throws Exception {
        return memberDao.selectAll();
    }


    public boolean add(Member member) throws Exception {
        boolean r = memberDao.insert(member);
        throw new Exception("自定义异常");
//        return r;
    }

    public boolean remove(long id) throws Exception {
        boolean r = memberDao.delete(id);
        throw new Exception("自定义异常");
//		return r;
    }

    public boolean modify(long id, String name) throws Exception {
        return memberDao.update(id, name);
    }

    public boolean login(long id, String name) throws Exception {
        boolean modify = this.modify(id, name);
//		throw new Exception("测试无事务");
        return modify;
    }

}
