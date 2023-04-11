package com.redmaple.mapper;

import java.util.List;

import com.redmaple.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PermissionMapper {

	@Select(" select * from sys_permission ")
	List<Permission> findAllPermission();

}
