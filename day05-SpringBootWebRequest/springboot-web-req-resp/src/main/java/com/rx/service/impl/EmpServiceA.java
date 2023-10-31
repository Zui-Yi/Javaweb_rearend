package com.rx.service.impl;

import com.rx.dao.EmpDao;
import com.rx.dao.impl.EmpDaoA;
import com.rx.pojo.Emp;
import com.rx.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component //将当前类交给ioc容器管理，成为ioc容器中的bean（注释掉component用于切换容器中的对象从A至B）
public class EmpServiceA implements EmpService {

    @Autowired //运行时，ioc容器会提供该类的类型的bean对象，并赋值给该变量 - 依赖注入
    private EmpDao empDao;

    @Override
    public List<Emp> listEmp() {
        //1. 调用dao, 获取数据
        List<Emp> empList = empDao.listEmp();

        //2. 对数据进行转换处理 - gender, job
        empList.stream().forEach(emp -> {
            //处理 gender 1: 男, 2: 女
            String gender = emp.getGender();
            if("1".equals(gender)){
                emp.setGender("男");
            }else if("2".equals(gender)){
                emp.setGender("女");
            }

            //处理job - 1: 讲师, 2: 班主任 , 3: 就业指导
            String job = emp.getJob();
            if("1".equals(job)){
                emp.setJob("讲师");
            }else if("2".equals(job)){
                emp.setJob("班主任");
            }else if("3".equals(job)){
                emp.setJob("就业指导");
            }
        });
        return empList;
    }
}
