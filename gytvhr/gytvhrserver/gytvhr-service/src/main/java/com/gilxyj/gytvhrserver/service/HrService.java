package com.gilxyj.gytvhrserver.service;


import com.gilxyj.gytvhrserver.bean.Hr;
import com.gilxyj.gytvhrserver.common.HrUtils;
import com.gilxyj.gytvhrserver.mapper.HrMapper;
import com.gilxyj.gytvhrserver.mapper.HrRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class HrService implements UserDetailsService {

    @Autowired
    HrMapper hrMapper;

    @Autowired
    HrRoleMapper hrRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(s);
        if (hr == null) {
            throw new UsernameNotFoundException("用户名不对");
        }
        return hr;
    }

    public int hrReg(String username, String password) {
        //如果用户名存在，返回错误
        if (hrMapper.loadUserByUsername(username) != null) {
            return -1;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(password);
        return hrMapper.hrReg(username, encode);
    }

    public List<Hr> getHrsByKeywords(String keywords) {
        return hrMapper.getHrsByKeywords(keywords);
    }

    public int updateHr(Hr hr) {
        return hrMapper.updateHr(hr);
    }

    public int updateHrRoles(Long hrId, Long[] rids) {
        int i = hrMapper.deleteRoleByHrId(hrId);
        return hrMapper.addRolesForHr(hrId, rids);
    }

    public Hr getHrById(Long hrId) {
        return hrMapper.getHrById(hrId);
    }

    public int deleteHr(Long hrId) {
        return hrMapper.deleteHr(hrId);
    }

    public List<Hr> getAllHrExceptAdmin(String keyword) {
        return hrMapper.getAllHr(HrUtils.getCurrentHr().getId(),keyword);
    }
    public List<Hr> getAllHr() {
        return hrMapper.getAllHr(null, null);
    }

    @Transactional
    public int updateHrRoles(Integer hrId, Integer[] roleId) {
        hrRoleMapper.deleteByHrid(hrId);
        if(roleId==null||roleId.length==0){
            return 0;
        }
        return hrRoleMapper.insertByHrIdAndRoleId(hrId,roleId);
    }


}
