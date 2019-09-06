package cn.appsys.dao.datadictionary;

import java.util.List;

import cn.appsys.pojo.DataDictionary;
import org.apache.ibatis.annotations.Param;

public interface DataDictionaryMapper {

    public List<DataDictionary> getDataDictionaryList(@Param("typeCode")String typeCode)throws Exception;
}
