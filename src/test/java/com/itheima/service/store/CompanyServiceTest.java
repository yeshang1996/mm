package com.itheima.service.store;

import com.itheima.domain.store.Company;
import com.itheima.service.store.impl.CompanyServiceImpl;
import org.junit.Test;

public class CompanyServiceTest {

    @Test
    public void testSave(){
        CompanyServiceImpl companyService = new CompanyServiceImpl();
        Company company = new Company();
        company.setName("测试数据");
        companyService.save(company);
    }

}
