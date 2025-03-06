package func.dao;

import java.util.List;

abstract public class FuncDAO<EntityType, KeyType> {

    abstract public void insert(EntityType entity);

    abstract public void update(EntityType entity);

    abstract public void delete(EntityType entity);

    abstract public EntityType readById(KeyType id);

    abstract public List<EntityType> selectAll();

    abstract public List<EntityType> selectBySql(String sql, Object... arg);

}
