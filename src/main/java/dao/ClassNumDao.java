package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;

public class ClassNumDao extends Dao {

    // 1件取得 ← ここに追加
    public ClassNum get(
            String schoolCd,
            String classNum)
            throws Exception {

        ClassNum c = null;

        Connection connection =
                getConnection();

        PreparedStatement statement =
                null;

        try {

            statement =
                connection.prepareStatement(

                "select * from class_num " +
                "where school_cd=? " +
                "and class_num=?"
            );

            statement.setString(1, schoolCd);
            statement.setString(2, classNum);

            ResultSet rSet =
                    statement.executeQuery();

            if (rSet.next()) {

                c = new ClassNum();

                c.setSchoolCd(
                    rSet.getString("school_cd")
                );

                c.setClassNum(
                    rSet.getString("class_num")
                );
            }

        } finally {

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

        return c;
    }

    // 一覧取得
    public List<ClassNum> filter(String schoolCd)
            throws Exception {

        List<ClassNum> list =
                new ArrayList<>();

        Connection connection =
                getConnection();

        PreparedStatement statement =
                null;

        try {

            statement =
                connection.prepareStatement(

                "select * from class_num " +
                "where school_cd=? " +
                "order by class_num"
            );

            statement.setString(1, schoolCd);

            ResultSet rSet =
                    statement.executeQuery();

            while (rSet.next()) {

                ClassNum c =
                        new ClassNum();

                c.setSchoolCd(
                    rSet.getString("school_cd")
                );

                c.setClassNum(
                    rSet.getString("class_num")
                );

                list.add(c);
            }

        } finally {

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

        return list;
    }

    // 保存
    public boolean save(ClassNum classNum)
            throws Exception {

        Connection connection =
                getConnection();

        PreparedStatement statement =
                null;

        int count = 0;

        try {

            statement =
                connection.prepareStatement(

                "insert into class_num " +
                "(school_cd, class_num) " +
                "values(?, ?)"
            );

            statement.setString(
                1,
                classNum.getSchoolCd()
            );

            statement.setString(
                2,
                classNum.getClassNum()
            );

            count =
                statement.executeUpdate();

        } finally {

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

        return count > 0;
    }
    
 // 更新
    public boolean update(
            String schoolCd,
            String oldClassNum,
            String newClassNum)
            throws Exception {

        Connection connection =
                getConnection();

        PreparedStatement statement =
                null;

        int count = 0;

        try {

            statement =
                connection.prepareStatement(

                "update class_num " +
                "set class_num=? " +
                "where school_cd=? " +
                "and class_num=?"
            );

            statement.setString(
                1,
                newClassNum
            );

            statement.setString(
                2,
                schoolCd
            );

            statement.setString(
                3,
                oldClassNum
            );

            count =
                statement.executeUpdate();

        } finally {

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

        return count > 0;
    }
 // 全件取得
    public List<ClassNum> all()
            throws Exception {

        List<ClassNum> list =
                new ArrayList<>();

        Connection connection =
                getConnection();

        PreparedStatement statement =
                null;

        try {

            statement =
                connection.prepareStatement(

                "select * from class_num " +
                "order by school_cd, class_num"
            );

            ResultSet rSet =
                    statement.executeQuery();

            while (rSet.next()) {

                ClassNum c =
                        new ClassNum();

                c.setSchoolCd(
                    rSet.getString("school_cd")
                );

                c.setClassNum(
                    rSet.getString("class_num")
                );

                list.add(c);
            }

        } finally {

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

        return list;
    }
 // 削除
    public boolean delete(
            String schoolCd,
            String classNum)
            throws Exception {

        Connection connection =
                getConnection();

        PreparedStatement statement =
                null;

        int count = 0;

        try {

            statement =
                connection.prepareStatement(

                "delete from class_num " +
                "where school_cd=? " +
                "and class_num=?"
            );

            statement.setString(
                1,
                schoolCd
            );

            statement.setString(
                2,
                classNum
            );

            count =
                statement.executeUpdate();

        } finally {

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

        return count > 0;
    }
    
}