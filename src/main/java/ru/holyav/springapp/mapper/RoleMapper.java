package ru.holyav.springapp.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.holyav.springapp.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getInt("id"));
        role.setRoleName(resultSet.getString("roleName"));
        return role;
    }
}
