package com.itheima.service.store;

import com.itheima.domain.store.Company;
import com.itheima.service.store.impl.CompanyServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CompanyServiceTest {

    private  CompanyServiceImpl companyService =null;

    @Before
    public void init(){
        CompanyServiceImpl companyService = new CompanyServiceImpl();
    }

    @Test
    public void testSave(){
        Company company = new Company();
        company.setName("测试数据1");
        companyService.save(company);
    }

    @After
    public void destory(){
        companyService =null;
    }

}
