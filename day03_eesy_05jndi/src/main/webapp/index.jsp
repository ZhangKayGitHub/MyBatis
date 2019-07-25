<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="com.kay.dao.IUserDao" %>
<%@ page import="com.kay.domain.User" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<html>
<body>
<h2>Hello World!</h2>
<%

    InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");

    SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
    SqlSessionFactory factory = factoryBuilder.build(in);

    SqlSession sqlSession = factory.openSession();

    IUserDao userDao = sqlSession.getMapper(IUserDao.class);

    List<User> users = userDao.findAll();
    for (User user : users){
        System.out.println(user);
    }
    //6.閲婃斁璧勬簮
    sqlSession.close();
    in.close();
%>
</body>
</html>
