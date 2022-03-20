package com.var.stress.dao;

import com.var.stress.domain.ParentCase;
import com.var.stress.domain.shiro.User;

import java.util.List;

public interface ParentCaseDao {

    public void saveParentCase(ParentCase parentCase);

    public void saveBathParentCase(List<ParentCase> parentCases);

    public void delCaseById(String id);

    public int updateCaseById(ParentCase parentCase);

    public List<ParentCase> findCaseByUserId(String userId);

}
