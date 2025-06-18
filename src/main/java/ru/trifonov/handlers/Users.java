package ru.trifonov.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.trifonov.users.User;

import java.util.ArrayList;
import java.util.List;


@Repository
public class Users {
    private static final List<String> columns = List.of("id", "username", "password", "access");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findById(long id){
        Object[] findArgument = new Object[]{"id", id};
        ArrayList<User> arr = find(findArgument);
        if(arr == null || arr.isEmpty()){return null;}
        return arr.getFirst();
    }
    public User findByUsername(String username){
        Object[] findArgument = new Object[]{"username", username};
        ArrayList<User> arr = find(findArgument);
        if(arr == null || arr.isEmpty()){return null;}
        return arr.getFirst();
    }

    private ArrayList<User> find(Object[] variables){
        if(!((new ArrayList<String>(List.of(new String[]{"id", "username"}))).contains(variables[0]))){
            return null;
        }
        String sql = "SELECT * FROM users WHERE ? = ?";
        try{
            ArrayList<User> result = new ArrayList<>();
            User user = jdbcTemplate.queryForObject(sql, variables, (rs, rowNum) -> {
                User res = new User(rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("access"));
                return res;
            });
            result.add(user);
            return result;

        } catch (Exception e){
            System.err.println(e.getStackTrace());
        }
        return null;
    }

    private void setData(long id, String column, Object data){
        if(!columns.contains(column)) throw new IllegalArgumentException("Недопустимое имя столбца: " + column);
        String sql = "UPDATE users SET " + column + " = ? WHERE id = ?";
        try{
            jdbcTemplate.update(sql, data, id);
        } catch (DataAccessException e) {
            System.err.println("Ошибка при обновлении данных: " + e.getMessage());
        }
    }
}
