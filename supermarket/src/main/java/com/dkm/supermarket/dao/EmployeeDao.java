package com.dkm.supermarket.dao;

import com.dkm.supermarket.bean.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao {
    public int insertEmp(@Param("employee") Employee employee, @Param("newPassword") String newPasswoed);
    public int updateEmp(Employee employee);
    public int updateEmpIsDel(@Param("id") Long id, @Param("isDelete") Integer isDelete);
    public int updateEmpPW(@Param("employee") Employee employee, @Param("newPassword") String newPasswoed);
    public Employee getEmp(Long id);
    public Employee checkPW(@Param("id") Long id, @Param("password") String password);
    public List<Employee> selectAllEmp();
    public List<String> selectEmpAuthority(Long id);
}
