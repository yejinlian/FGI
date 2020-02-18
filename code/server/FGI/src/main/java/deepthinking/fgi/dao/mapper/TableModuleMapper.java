package deepthinking.fgi.dao.mapper;

import deepthinking.fgi.domain.TableModule;
import deepthinking.fgi.domain.TableModuleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface TableModuleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_module
     *
     * @mbg.generated
     */
    @SelectProvider(type=TableModuleSqlProvider.class, method="countByExample")
    long countByExample(TableModuleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_module
     *
     * @mbg.generated
     */
    @DeleteProvider(type=TableModuleSqlProvider.class, method="deleteByExample")
    int deleteByExample(TableModuleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_module
     *
     * @mbg.generated
     */
    @Delete({
        "delete from table_module",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_module
     *
     * @mbg.generated
     */
    @Insert({
        "insert into table_module (ID, ModuleName, ",
        "SqlUrl, Tab, ModuleGroup, ",
        "Des, Remark)",
        "values (#{id,jdbcType=INTEGER}, #{modulename,jdbcType=VARCHAR}, ",
        "#{sqlurl,jdbcType=VARCHAR}, #{tab,jdbcType=VARCHAR}, #{modulegroup,jdbcType=VARCHAR}, ",
        "#{des,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR})"
    })
    int insert(TableModule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_module
     *
     * @mbg.generated
     */
    @InsertProvider(type=TableModuleSqlProvider.class, method="insertSelective")
    int insertSelective(TableModule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_module
     *
     * @mbg.generated
     */
    @SelectProvider(type=TableModuleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ModuleName", property="modulename", jdbcType=JdbcType.VARCHAR),
        @Result(column="SqlUrl", property="sqlurl", jdbcType=JdbcType.VARCHAR),
        @Result(column="Tab", property="tab", jdbcType=JdbcType.VARCHAR),
        @Result(column="ModuleGroup", property="modulegroup", jdbcType=JdbcType.VARCHAR),
        @Result(column="Des", property="des", jdbcType=JdbcType.VARCHAR),
        @Result(column="Remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<TableModule> selectByExample(TableModuleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_module
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "ID, ModuleName, SqlUrl, Tab, ModuleGroup, Des, Remark",
        "from table_module",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ModuleName", property="modulename", jdbcType=JdbcType.VARCHAR),
        @Result(column="SqlUrl", property="sqlurl", jdbcType=JdbcType.VARCHAR),
        @Result(column="Tab", property="tab", jdbcType=JdbcType.VARCHAR),
        @Result(column="ModuleGroup", property="modulegroup", jdbcType=JdbcType.VARCHAR),
        @Result(column="Des", property="des", jdbcType=JdbcType.VARCHAR),
        @Result(column="Remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    TableModule selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_module
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TableModuleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TableModule record, @Param("example") TableModuleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_module
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TableModuleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TableModule record, @Param("example") TableModuleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_module
     *
     * @mbg.generated
     */
    @UpdateProvider(type=TableModuleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TableModule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table table_module
     *
     * @mbg.generated
     */
    @Update({
        "update table_module",
        "set ModuleName = #{modulename,jdbcType=VARCHAR},",
          "SqlUrl = #{sqlurl,jdbcType=VARCHAR},",
          "Tab = #{tab,jdbcType=VARCHAR},",
          "ModuleGroup = #{modulegroup,jdbcType=VARCHAR},",
          "Des = #{des,jdbcType=VARCHAR},",
          "Remark = #{remark,jdbcType=VARCHAR}",
        "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TableModule record);
}