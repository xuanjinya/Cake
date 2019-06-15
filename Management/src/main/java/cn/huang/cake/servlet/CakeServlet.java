package cn.huang.cake.servlet;

import cn.huang.cake.entity.Cake;
import cn.huang.cake.entity.Category;
import cn.huang.cake.service.CakeService;
import cn.huang.cake.service.CategoryService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: Yaking
 * @Date: 2019/6/15 14:16
 * @Describe: 点击蛋糕分类时，蛋糕的信息展示（通过蛋糕分类的id来进行查询）
 */
public class CakeServlet extends HttpServlet {

    private CakeService cakeService;
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        super.init();
        cakeService = new CakeService();
        categoryService = new CategoryService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("/cake/list.do".equals(req.getServletPath())) {
            String categoryIdStr = req.getParameter("categoryId");
            try {
                Long categoryId = null;
                if (null != categoryIdStr) {
                    categoryId = Long.valueOf(categoryIdStr);
                }
                List<Cake> cakes = cakeService.getCakeByCategoryId(categoryId, 1, 5000);
                req.setAttribute("cakes", cakes);

                //取到全部的分类信息
                List<Category> categories = categoryService.getCategories();
                req.setAttribute("categories", categories);
                req.getRequestDispatcher("/WEB-INF/views/biz/cake_list.jsp").forward(req, resp);
            } catch (NumberFormatException e) {
                req.getRequestDispatcher("/WEB-INF/views/biz/cake_list.jsp").forward(req, resp);
            }
        } else if ("/cake/addPrompt.do".equals(req.getServletPath())) {
            //取到全部的分类信息
            List<Category> categories = categoryService.getCategories();
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/WEB-INF/views/biz/add.jsp").forward(req, resp);
        } else if ("/cake/add.do".equals(req.getServletPath())) {
            //新增一个蛋糕
            req.setCharacterEncoding("utf-8");
            //判断是不是附件上传的操作
            if (ServletFileUpload.isMultipartContent(req)) {
                try {
                    FileItemFactory factory = new DiskFileItemFactory();
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    List<FileItem> items = upload.parseRequest(req);//解析请求
                    Iterator<FileItem> ite = items.iterator();
                    Cake cake = new Cake();
                    while (ite.hasNext()) {
                        FileItem item = ite.next();
                        if (item.isFormField()) {//信息时普通的格式（指的时表单元素中的文本框，下拉列表之类的格式）
                            String filedName = item.getFieldName();//得到表单元素name的属性
                            if ("categoryId".equals(filedName)) {
                                cake.setCategoryId(Long.valueOf(item.getString()));
                            } else if ("level".equals(filedName)) {
                                cake.setLevel(Integer.valueOf(item.getString()));
                            } else if ("name".equals(filedName)) {
                                cake.setName(item.getString());
                            } else if ("price".equals(filedName)) {
                                cake.setPrice(Integer.valueOf(item.toString()));
                            }
                        } else {//信息是文件格式的（图片上传）
                            cake.setSmallImg(item.get());
                        }
                    }
                    cakeService.addCake(cake);
                    req.getRequestDispatcher("/cake/list.do").forward(req, resp);
                } catch (FileUploadException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    @Override
    public void destroy() {
        super.destroy();
        cakeService = null;
        categoryService = null;
    }
}
