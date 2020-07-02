package com.wzu.appmanager.service.developer;

import com.wzu.appmanager.pojo.AppInfo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AppInfoService {
	
	/**
	 * 新增app
	 * @param appInfo
	 * @return
	 * @throws Exception
	 */
	public boolean add(@Param(value = "softwareName")String softwarename,
					   @Param(value = "APKName" ) String apkname,
					   @Param(value = "supportROM") String supportrom,
					   @Param(value = "interfaceLanguage") String interfacelanguage,
					   @Param(value = "updateDate") Date updatedate,
					   @Param(value = "softwareSize") BigDecimal softwaresize,
					   @Param(value = "devId") Long devid,
					   @Param(value = "appInfo") String appinfo,
					   @Param(value = "status") Integer status,
					   @Param(value = "onSaleDate") Date onsaledate,
					   @Param(value = "offSaleDate") Date offsaledate,
					   @Param(value = "categoryLevel1") Long categorylevel1,
					   @Param(value = "categoryLevel2") Long categorylevel2,
					   @Param(value ="categoryLevel3" )Long categorylevel3,
					   @Param(value = "downloads") Long downloads,
					   @Param(value = "flatformId") Long flatformid,
					   @Param(value = "logoPicPath") String logopicpath,
					   @Param(value = "logoLocPath") String logolocpath,
					   @Param(value = "createdBy") Long createdby,
					   @Param(value = "creationDate") Date creationdate) throws Exception;
	/**
	 * 修改app信息
	 * @param appInfo
	 * @return
	 * @throws Exception
	 */
	public boolean modify(@Param(value = "id") Long id
			,@Param(value = "softwareName")String softwarename,
						  @Param(value = "APKName" ) String apkname,
						  @Param(value = "supportROM") String supportrom,
						  @Param(value = "interfaceLanguage") String interfacelanguage,
						  @Param(value = "updateDate") Date updatedate,
						  @Param(value = "softwareSize") BigDecimal softwaresize,
						  @Param(value = "devId") Long devid,
						  @Param(value = "appInfo") String appinfo,
						  @Param(value = "status") Integer status,
						  @Param(value = "onSaleDate") Date onsaledate,
						  @Param(value = "offSaleDate") Date offsaledate,
						  @Param(value = "categoryLevel1") Long categorylevel1,
						  @Param(value = "categoryLevel2") Long categorylevel2,
						  @Param(value ="categoryLevel3" )Long categorylevel3,
						  @Param(value = "downloads") Long downloads,
						  @Param(value = "flatformId") Long flatformid,
						  @Param(value = "logoPicPath") String logopicpath,
						  @Param(value = "logoLocPath") String logolocpath,
						  @Param(value = "createdBy") Long createdby,
						  @Param(value = "modifyBy") Long modifyBy,
						  @Param(value = "creationDate") Date creationdate,
						  @Param(value = "modifyDate") Date modifyDate)throws Exception;
	
	/**
	 * 根据appId删除app应用
	 * @param delId
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAppInfoById(Integer delId)throws Exception;
	
	/**
	 * 根据条件查询出app列表
	 * @param querySoftwareName
	 * @param queryStatus
	 * @param queryCategoryLevel1
	 * @param queryCategoryLevel2
	 * @param queryCategoryLevel3
	 * @param queryFlatformId
	 * @param devId
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<AppInfo> getAppInfoList(String querySoftwareName, Integer queryStatus,
                                        Integer queryCategoryLevel1, Integer queryCategoryLevel2,
                                        Integer queryCategoryLevel3, Integer queryFlatformId,
                                        Integer devId, Integer currentPageNo, Integer pageSize)throws Exception;

	/**
	 * 根据条件查询appInfo表记录数
	 * @param querySoftwareName
	 * @param queryStatus
	 * @param queryCategoryLevel1
	 * @param queryCategoryLevel2
	 * @param queryCategoryLevel3
	 * @param queryFlatformId
	 * @param devId
	 * @return
	 * @throws Exception
	 */
	public int getAppInfoCount(String querySoftwareName, Integer queryStatus,
                               Integer queryCategoryLevel1, Integer queryCategoryLevel2,
                               Integer queryCategoryLevel3, Integer queryFlatformId, Integer devId)throws Exception;
	/**
	 * 根据id、apkName查找appInfo
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AppInfo getAppInfo(Integer id, String APKName)throws Exception;
	
	/**
	 * 删除logo图片
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAppLogo(Integer id)throws Exception;
	
	/**
	 * 通过appId删除app应用(app_info、app_version)
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean appsysdeleteAppById(Integer id)throws Exception;
	
	
	/**
	 * update Sale Status By AppId and Operator
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public boolean appsysUpdateSaleStatusByAppId(AppInfo appInfo) throws Exception;

	//修改appinfo的appversion
	public boolean appversionmodify(Long id,Long appid);
}
