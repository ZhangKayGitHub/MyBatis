package com.kay.mybatis.cfg;
/**
 * 用于封装执行的SQL语句的和结果类型的全限定名
 */
public class Mapper {
    private String queryString;//SQL语句
    private String resultType;//实体类全限定名

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
