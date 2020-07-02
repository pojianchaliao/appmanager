package com.wzu.appmanager.dao;

import com.wzu.appmanager.pojo.DataDictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataDictionaryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_dictionary
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_dictionary
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    int insert(DataDictionary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_dictionary
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    DataDictionary selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_dictionary
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    List<DataDictionary> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_dictionary
     *
     * @mbggenerated Sat Jun 06 17:59:00 CST 2020
     */
    int updateByPrimaryKey(DataDictionary record);

    List<DataDictionary> getDataDictionaryList(@Param("typecode")String typeCode);
}