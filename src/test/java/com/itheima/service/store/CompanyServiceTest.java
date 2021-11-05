package com.itheima.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Company;
import com.itheima.service.store.impl.CompanyServiceImpl;
import org.apache.ibatis.ognl.DynamicSubscript;
import org.junit.*;

import java.util.List;

import static org.apache.ibatis.ognl.DynamicSubscript.all;

public class CompanyServiceTest {

    private static CompanyService companyService =null;

    @BeforeClass
    public static void init(){
        companyService = new CompanyServiceImpl();
    }

    @Test
    public void testSave(){
        Company company = new Company();
        company.setName("测试数据5");
        companyService.save(company);
    }

    @Test
    public void testFindAll(){
        PageInfo all = companyService.findAll(1, 100);
        System.out.println(all);
    }

    @AfterClass
    public static void destroy(){
        companyService =null;
    }

}
