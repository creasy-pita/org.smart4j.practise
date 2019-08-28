package org.smart4j.framework.helper;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framwork.util.PropsUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 *
 * Created by creasypita on 8/24/2019.
 */
public class DatabaseHelper {
    private final static Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
    private static final ThreadLocal<Connection> CONNECTION_HOLDER;
    private static  final QueryRunner QUERY_RUNNER;
    private static final BasicDataSource DATA_SOURCE;

    static{
        CONNECTION_HOLDER = new ThreadLocal<Connection>();
        QUERY_RUNNER = new QueryRunner();

        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(ConfigHelper.getJdbcDriver());
        DATA_SOURCE.setUrl(ConfigHelper.getJdbcUrl());
        DATA_SOURCE.setUsername(ConfigHelper.getJdbcUserName());
        DATA_SOURCE.setPassword(ConfigHelper.getJdbcPassword());
    }

    public static Connection getConnection()
    {
        Connection conn = CONNECTION_HOLDER.get();
        if(conn ==null)
        {
            try {
                conn = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                LOGGER.error("get connection failure", e);
                throw new RuntimeException(e);
            }
            finally {
                CONNECTION_HOLDER.set(conn);
            }
        }
        return conn;
    }

    public static void beginTransaction(){
        Connection conn = getConnection();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("set auto commit failure", e);
            throw new RuntimeException(e);
        }
    }

    public static void commitTransaction(){
        Connection conn = getConnection();
        try {
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            LOGGER.error("commit failure", e);
            throw new RuntimeException(e);
        }finally {
            CONNECTION_HOLDER.remove();
        }
    }

    public static void rollbackTransaction(){
        Connection conn = getConnection();
        try {
            conn.rollback();
            conn.close();
        } catch (SQLException e) {
            LOGGER.error("rollback failure", e);
            throw new RuntimeException(e);
        }finally {
            CONNECTION_HOLDER.remove();
        }

    }

    public static  <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object[] params)
    {
        List<T> entityList;
        Connection conn = getConnection();
        try {
            entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            LOGGER.error("query entity list failure", e);
            throw new RuntimeException(e);
        }
        return entityList;
    }

    public static <T> T queryEntity(Class<T> entityClass,String sql, Object... params)
    {
        T entity;
        Connection conn = getConnection();
        try {
            entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<T>(entityClass), params);
        } catch (SQLException e) {
            LOGGER.error("query entity failure", e);
            throw new RuntimeException(e);
        }
        return entity;
    }

    public static <T> boolean updateEntity(Class<T> entityClass,long id, Map<String, Object> fieldMap)
    {
        String sql = "UPDATE " + entityClass.getSimpleName() + " SET ";
        StringBuilder columns = new StringBuilder();
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append(" = ?, ");
        }
        sql += columns.substring(0, columns.lastIndexOf(", ")) + " WHERE id = ?";

        List<Object> paramList = new ArrayList<Object>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);
        Object[] params = paramList.toArray();

        return update(sql, params) == 1;
    }

    public static <T> boolean deleteEntity(Class<T> entityClass, long id)
    {
        String sql = "DELETE FROM " +  entityClass.getSimpleName() + " WHERE id=?";
        return update(sql, id)==1;
    }


    public static int update(String sql,Object... params)
    {

        int rows = -1;
        Connection conn = getConnection();
        try {
            rows = QUERY_RUNNER.update(conn, sql, params);
        } catch (SQLException e) {
            LOGGER.error("update failure",e);
            throw new RuntimeException(e);
        }
        return rows;
    }

    public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap)
    {
        String sql = "INSERT INTO " + entityClass.getSimpleName();
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append(", ");
            values.append("?, ");
        }
        columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
        values.replace(values.lastIndexOf(", "), values.length(), ")");
        sql += columns + " VALUES " + values;

        Object[] params = fieldMap.values().toArray();

        return update(sql, params) == 1;
    }
}
