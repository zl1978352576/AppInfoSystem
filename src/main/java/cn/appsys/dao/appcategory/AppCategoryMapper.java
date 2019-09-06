package cn.appsys.dao.appcategory;

import java.util.List;

import cn.appsys.pojo.AppCategory;
import org.apache.ibatis.annotations.Param;

public interface AppCategoryMapper {

    public List<AppCategory> getAppCategoryListByParentId(@Param("parentId")Integer parentId)throws Exception;
}
