package com.kirov7.layer.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-01-24 19:32
 */

public class JdbcUtil {

    private static final String url = "jdbc:mysql://localhost:3306/exercise?severTimezone=Asia/Shanghai";
    private static final String username = "root";
    private static final String password = "root";

    //使用静态代码块，待类加载时执行驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("驱动程序未加载");
        }
    }

    /**
     * 万能更新
     * @param sql
     * @param params
     * @return
     */
    public static int update(String sql, Object... params) {
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            //
            preparedStatement = createPreparedStatement(connection, sql, params);
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement, connection);
        }
        return result;
    }

    /**
     * sql语句要和字段匹配
     * 万能查询通过反射实现，必须保证类中定义字段名与查询结果展示的列名称保持一直
     *
     * @param sql
     * @param clazz
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> query(String sql, Class<T> clazz, Object... params) {

        List<T> dataList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            preparedStatement = createPreparedStatement(connection, sql, params);
            resultSet = preparedStatement.executeQuery();
            //结果集元数据
            while (resultSet.next()) {
                T t = createInstance(clazz, resultSet);
                dataList.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(resultSet, preparedStatement, connection);
        }
        return dataList;
    }

    /**
     * 创建预编译处理器
     * @param connection
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    private static PreparedStatement createPreparedStatement(Connection connection, String sql, Object... params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //加入需要查询的值 params
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }
        return preparedStatement;
    }

    /**
     * 关闭连接,执行器,结果集
     *
     * @param closeables
     */
    private static void close(AutoCloseable... closeables) {
        if (closeables != null && closeables.length > 0) {
            for (AutoCloseable closeable : closeables) {
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

    /**
     * 反射创建对象
     * @param clazz
     * @param resultSet 查询结果集
     * @param <T>
     * @return
     * @throws Exception
     */
    private static<T> T createInstance(Class<T> clazz, ResultSet resultSet) throws Exception {
        Constructor<T> constructor = clazz.getConstructor();//获取无参构造
        T t = constructor.newInstance();//创建对象
        Field[] fields = clazz.getDeclaredFields();//获取类中定义的字段
        for (Field field : fields) {
            String fieldName = field.getName();
            String methodNme = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method method = clazz.getDeclaredMethod(methodNme, field.getType());
            try {
                Object value = resultSet.getObject(fieldName, field.getType());
                method.invoke(t, value);
            } catch (Exception e) {
            }
        }
        return t;
    }
}
