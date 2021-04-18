package com.project.sportsgeek.repository.rolerepo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.sportsgeek.model.profile.Role;

@Repository(value = "roleRepo")
public interface RoleRepository {

    public List<Role> findAllRole();
    public List<Role> findRoleById(int id) throws Exception;
    public int addRole(Role role) throws Exception;
    public boolean updateRole(int id, Role role) throws Exception;
    public int deleteRole(int id) throws Exception;
}
