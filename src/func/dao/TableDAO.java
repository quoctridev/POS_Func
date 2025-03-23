/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dao;

import func.dao.FuncDAO;
import func.entity.TableEntity;
import func.utils.Database;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Set;
/**
 *
 * @author HOANG NHI
 */
public class TableDAO extends FuncDAO<TableEntity, String> {

 
    public void insert(TableEntity entity) {
        String sql = "INSERT INTO Tables (table_number, capacity, [zone]) VALUES (?, ?, ?)";
        Database.update(sql, entity.getTableNumber(), entity.getCapacity(), entity.getZone());
    }

 
    public void update(TableEntity entity) {
        String sql = "UPDATE Tables SET table_number=?, capacity=?, [zone]=? WHERE table_id = ?";
        Database.update(sql, entity.getTableNumber(), entity.getStatus(), entity.getCapacity(), entity.getZone());
    }


    public void delete(String id) {
        String sql = "DELETE FROM Tables WHERE table_id = ? ";
        Database.update(sql, id);
    }


    public TableEntity selectById(String id) {
    String sql = "SELECT TOP 1 FROM Tables WHERE table_id = ?";
    List<TableEntity> list = selectBySql(sql, id);
    return list.size() > 0 ? list.get(0) : null;
    }

 
    public List<TableEntity> selectAll() {
     String sql = "SELECT * FROM Tables";
     return selectBySql(sql);
    }

   
    protected List<TableEntity> selectBySql(String sql, Object... args) {
       List<TableEntity> list = new ArrayList<>();
       try{
           ResultSet rs = null;
           try{
               rs = Database.query(sql, args);
               while(rs.next()){
                   TableEntity tb = new TableEntity();
                   tb.setTableId(rs.getInt("table_id"));
                   tb.setTableNumber(rs.getString("table_number"));
                   tb.setStatus(rs.getString("status"));
                   tb.setCapacity(rs.getInt("capacity"));
                   tb.setZone(rs.getString("zone"));
                   tb.setCreatedAt(rs.getDate("created_at"));
                   list.add(tb);
                      
               }           
           } finally{
               rs.getStatement().getConnection().close();
           }
       }catch(SQLException e){
           e.printStackTrace();
           throw new RuntimeException(e);
       }
       return list;
    }
    
}
