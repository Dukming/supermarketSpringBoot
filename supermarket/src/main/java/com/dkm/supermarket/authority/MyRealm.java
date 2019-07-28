package com.dkm.supermarket.authority;



import com.dkm.supermarket.bean.Employee;
import com.dkm.supermarket.dao.EmployeeDao;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.List;

public class MyRealm extends AuthorizingRealm {
    @Resource(name="employeeDao")
    private EmployeeDao employeeDao;
    //设置realm的名称
    @Override
    public void setName(String name) {
        super.setName("myRealm");
    }
    //授权身份
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        Long id=(long) principalCollection.getPrimaryPrincipal();
        Cache cache=getAuthorizationCache();
        List<String> authorities=(List<String>) cache.get(id+"Authorities");
        String role=(String) cache.get(id+"Role");
        if (authorities == null) {
           Employee emp = employeeDao.getEmp(id);
            authorities = employeeDao.selectEmpAuthority(id);
            role = emp.getType().toString();
            for (String s:authorities
            ) {
                System.out.println(s);
            }
            cache.put(id+"Authorities",authorities);
            cache.put(id+"Role",role);
        }
        SimpleAuthorizationInfo sai=new SimpleAuthorizationInfo();
        System.out.println(role);
        sai.addRole(role);
        sai.addStringPermissions(authorities);
        return sai;
    }
    //认证身份
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String s=(String) authenticationToken.getPrincipal();
        Long userId=Long.parseLong(s);
        Cache cache=getAuthenticationCache();
        String passWord=(String) cache.get(userId+"PassWord");
        String salt=(String) cache.get(userId+"Salt");
        if(passWord==null){
            Employee emp = employeeDao.getEmp(userId);
            passWord=emp.getPassword();
            salt=emp.getSalt();
            cache.put(userId+"PassWord",passWord);
            cache.put(userId+"Salt",salt);
        }

        SimpleAuthenticationInfo sai=new SimpleAuthenticationInfo(userId,passWord,new MySimpleByteSource(salt),getName());
        return sai;
    }

}