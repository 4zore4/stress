package com.var.stress.dao;

import com.var.stress.domain.ChildrenCase;

import java.util.List;

public interface ChildrenCaseDao {

    public void saveParentCase(ChildrenCase childrenCase);

    public void saveBathParentCase(List<ChildrenCase> childrenCase);

    public void delCaseById(String id);

    public int updateCaseById(ChildrenCase childrenCase);

    public List<ChildrenCase> findCaseByParentId(String parentId);
    
}
