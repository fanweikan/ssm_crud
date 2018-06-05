package com.atguigu.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.dao.DepartmentMapper;
import com.atguigu.crud.dao.EmployeeMapper;

/**
 * 测试dao层工作
 * @author 樊卫宽
 *推荐spring的项目就可以使用spring的单元测试，可以自动注入我们需要的组件
 *1.导入springTest模块,通过pom.xml配置
 *2. @ContextConfiguration指定spring配置文件的位置
 * 3. 直接autowired找对象
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	private DepartmentMapper dept;
	@Autowired
	private EmployeeMapper emp;
	@Autowired
	SqlSession sqlSession;
	/**
	 * 测试departmentMapper
	 */
	@Test
	public void testCRUD() {
		/*//1.创建springIoc容器
		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2.从容器中获取mapper
		DepartmentMapper bean = ioc.getBean(DepartmentMapper.class); */
		System.out.println(dept);
		//插入几个部门
		dept.insertSelective(new Department(null,"开发部"));
		dept.insertSelective(new Department(null,"测试部"));
		//2. 生成一个员工数据
				//emp.insertSelective(new Employee(null,"jack","M","jack@126.com",1));
				//3. 批量插入员工，使用可以执行批量操作的sqlSession操作 

		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i = 0;i<1000;i++) {
			String name = UUID.randomUUID().toString().substring(0, 5) + i;
			mapper.insertSelective(new Employee(null,name,"M",name+"@126.com",1));
		}
	}

}
