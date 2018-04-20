package com.qjk.dao;

import com.qjk.pojo.UserPo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserPo,Integer> {
}
