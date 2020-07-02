package com.wzu.appmanager.service.developer;

import com.wzu.appmanager.dao.AppCategoryMapper;
import com.wzu.appmanager.pojo.AppCategory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppCategoryServiceImpl implements AppCategoryService {

	@Resource
	private AppCategoryMapper mapper;
	
	@Override
	public List<AppCategory> getAppCategoryListByParentId(Integer parentId)
			throws Exception {
		// TODO Auto-generated method stub
		return mapper.getAppCategoryListByParentId(parentId);
	}

}
