package com.itheima.web.controller.company;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Company;
import com.itheima.service.store.CompanyService;
import com.itheima.service.store.impl.CompanyServiceImpl;
import com.itheima.utils.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//uri:/store/company?operation=list
@WebServlet("/store/company")
public class CompanyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if("list".equals(operation)){
            this.list(request,response);
        }else if ("toAdd".equals(operation)){
            this.toAdd(request,response);
        }else if ("save".equals(operation)){
            this.save(request,response);
        }else if ("list".equals(operation)){

        }else if ("list".equals(operation)){

        }
    }

    private void list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //进入列表页
        //获取数据
        CompanyService companyService = new CompanyServiceImpl();
        int page = 1;
        int size = 5;
        if(StringUtils.isNotBlank(request.getParameter("page"))){
            page=Integer.parseInt(request.getParameter("page"));
        }
        if(StringUtils.isNotBlank(request.getParameter("size"))){
            size=Integer.parseInt(request.getParameter("size"));
        }
        PageInfo all = companyService.findAll(page,size);
        //将数据保存到指定的位置
        request.setAttribute("page",all);
        //跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/company/list.jsp").forward(request,response);
    }

    private void toAdd(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/store/company/add.jsp").forward(request,response);
    }

    private void save(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //获取数据，封装成一个对象
        Company company = BeanUtil.fillBean(request,Company.class,"yyyy-MM-dd");
        //调用业务层save
        CompanyService companyService = new CompanyServiceImpl();
        companyService.save(company);
        //跳转回页面list
        //list(request,response);
        response.sendRedirect(request.getContextPath()+"/store/company?operation=list");

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
