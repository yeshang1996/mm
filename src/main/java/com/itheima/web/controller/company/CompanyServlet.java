package com.itheima.web.controller.company;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.store.Company;
import com.itheima.service.store.CompanyService;
import com.itheima.service.store.impl.CompanyServiceImpl;
import com.itheima.utils.BeanUtil;
import com.itheima.web.controller.BaseServlet;
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
public class CompanyServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if("list".equals(operation)){
            this.list(request,response);
        }else if ("toAdd".equals(operation)){
            this.toAdd(request,response);
        }else if ("save".equals(operation)){
            this.save(request,response);
        }else if ("toEdit".equals(operation)){
            this.toEdit(request,response);
        }else if ("edit".equals(operation)){
            this.edit(request,response);
        }else if ("delete".equals(operation)){
            this.delete(request,response);
        }
    }




    private void list(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //进入列表页
        //获取数据
//        CompanyService companyService = new CompanyServiceImpl();
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
//        CompanyService companyService = new CompanyServiceImpl();
        companyService.save(company);
        //跳转回页面list
        //list(request,response);
        response.sendRedirect(request.getContextPath()+"/store/company?operation=list");
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询要修改的数据findById
        String id = request.getParameter("id");
//        CompanyService companyService = new CompanyServiceImpl();
        Company company = companyService.findById(id);
        //将数据加载到指定区域，供页面获取;
        request.setAttribute("company",company);
        //页面跳转
        request.getRequestDispatcher("/WEB-INF/pages/store/company/update.jsp").forward(request,response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取数据，封装成一个对象
        Company company = BeanUtil.fillBean(request,Company.class,"yyyy-MM-dd");
        //调用业务层save
//        CompanyService companyService = new CompanyServiceImpl();
        companyService.update(company);
        //跳转回页面list
        //list(request,response);
        response.sendRedirect(request.getContextPath()+"/store/company?operation=list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取数据，封装成一个对象
        Company company = BeanUtil.fillBean(request,Company.class);
        //调用业务层save
//        CompanyService companyService = new CompanyServiceImpl();
        companyService.delete(company);
        //跳转回页面list
        //list(request,response);
        response.sendRedirect(request.getContextPath()+"/store/company?operation=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        this.doGet(request, response);
    }
}
