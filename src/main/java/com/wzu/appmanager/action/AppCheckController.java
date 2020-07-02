package com.wzu.appmanager.action;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.io.FilenameUtils;
import com.wzu.appmanager.pojo.*;
import com.wzu.appmanager.service.backend.AppService;
import com.wzu.appmanager.service.developer.AppCategoryService;
import com.wzu.appmanager.service.developer.AppInfoService;
import com.wzu.appmanager.service.developer.AppVersionService;
import com.wzu.appmanager.service.developer.DataDictionaryService;
import com.wzu.appmanager.util.Constants;
import com.wzu.appmanager.util.PageSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value="/manager/backend/app")
public class AppCheckController {
	private Logger logger = Logger.getLogger(AppCheckController.class);
	
	@Resource
	private AppService appService;
	@Resource
	private AppInfoService appInfoService;
	@Resource
	private AppVersionService appVersionService;
	@Resource 
	private DataDictionaryService dataDictionaryService;
	@Resource 
	private AppCategoryService appCategoryService;
//	获取审核内容
	@RequestMapping(value="/list")
	public String getAppInfoList(Model model, HttpSession session,
								 @RequestParam(value="querySoftwareName",required=false) String querySoftwareName,
								 @RequestParam(value = "devId",required = false) Integer devId,
								 @RequestParam(value = "queryStatus",required = false) Integer queryStatus,
								 @RequestParam(value="queryCategoryLevel1",required=false) String _queryCategoryLevel1,
								 @RequestParam(value="queryCategoryLevel2",required=false) String _queryCategoryLevel2,
								 @RequestParam(value="queryCategoryLevel3",required=false) String _queryCategoryLevel3,
								 @RequestParam(value="queryFlatformId",required=false) String _queryFlatformId,
								 @RequestParam(value="pageIndex",required=false) String pageIndex){
		
		logger.info("getAppInfoList -- > querySoftwareName: " + querySoftwareName);
		logger.info("getAppInfoList -- > queryCategoryLevel1: " + _queryCategoryLevel1);
		logger.info("getAppInfoList -- > queryCategoryLevel2: " + _queryCategoryLevel2);
		logger.info("getAppInfoList -- > queryCategoryLevel3: " + _queryCategoryLevel3);
		logger.info("getAppInfoList -- > queryFlatformId: " + _queryFlatformId);
		logger.info("getAppInfoList -- > pageIndex: " + pageIndex);
		
		List<AppInfo> appInfoList = null;
		List<DataDictionary> flatFormList = null;
		List<AppCategory> categoryLevel1List = null;//列出一级分类列表，注：二级和三级分类列表通过异步ajax获取
		List<AppCategory> categoryLevel2List = null;
		List<AppCategory> categoryLevel3List = null;
		//页面容量
		int pageSize = Constants.pageSize;
		//当前页码
		Integer currentPageNo = 1;
		
		if(pageIndex != null){
			try{
				currentPageNo = Integer.valueOf(pageIndex);
			}catch (NumberFormatException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		Integer queryCategoryLevel1 = null;
		if(_queryCategoryLevel1 != null && !_queryCategoryLevel1.equals("")){
			queryCategoryLevel1 = Integer.parseInt(_queryCategoryLevel1);
		}
		Integer queryCategoryLevel2 = null;
		if(_queryCategoryLevel2 != null && !_queryCategoryLevel2.equals("")){
			queryCategoryLevel2 = Integer.parseInt(_queryCategoryLevel2);
		}
		Integer queryCategoryLevel3 = null;
		if(_queryCategoryLevel3 != null && !_queryCategoryLevel3.equals("")){
			queryCategoryLevel3 = Integer.parseInt(_queryCategoryLevel3);
		}
		Integer queryFlatformId = null;
		if(_queryFlatformId != null && !_queryFlatformId.equals("")){
			queryFlatformId = Integer.parseInt(_queryFlatformId);
		}
		
		//总数量（表）
		int totalCount = 0;
		try {
			totalCount = appService.getAppInfoCount(querySoftwareName, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryFlatformId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//总页数
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		//控制首页和尾页
		if(currentPageNo < 1){
			currentPageNo = 1;
		}else if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}

//		区分查找的是全部app还是未审核app
		if (session.getAttribute("login_type").equals("common")){
			try {
				appInfoList = appInfoService.getAppInfoList(querySoftwareName,queryStatus, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryFlatformId,devId,currentPageNo, pageSize);
				flatFormList = this.getDataDictionaryList("APP_FLATFORM");
				categoryLevel1List = appCategoryService.getAppCategoryListByParentId(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
		try {
			appInfoList = appService.getAppInfoList(querySoftwareName, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryFlatformId, currentPageNo, pageSize);
			flatFormList = this.getDataDictionaryList("APP_FLATFORM");
			categoryLevel1List = appCategoryService.getAppCategoryListByParentId(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		model.addAttribute("appInfoList", appInfoList);
		model.addAttribute("flatFormList", flatFormList);
		model.addAttribute("categoryLevel1List", categoryLevel1List);
		model.addAttribute("pages", pages);
		model.addAttribute("querySoftwareName", querySoftwareName);
		model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
		model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
		model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
		model.addAttribute("queryFlatformId", queryFlatformId);
		
		//二级分类列表和三级分类列表---回显
		if(queryCategoryLevel2 != null && !queryCategoryLevel2.equals("")){
			categoryLevel2List = getCategoryList(queryCategoryLevel1.toString());
			model.addAttribute("categoryLevel2List", categoryLevel2List);
		}
		if(queryCategoryLevel3 != null && !queryCategoryLevel3.equals("")){
			categoryLevel3List = getCategoryList(queryCategoryLevel2.toString());
			model.addAttribute("categoryLevel3List", categoryLevel3List);
		}
		if (session.getAttribute("login_type").equals("common"))
		{
			return "dev/applist";
		}
		else{
		return "backend/applist";
		}
	}

	public List<DataDictionary> getDataDictionaryList(String typeCode){
		List<DataDictionary> dataDictionaryList = null;
		try {
			dataDictionaryList = dataDictionaryService.getDataDictionaryList(typeCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataDictionaryList;
	}
	
	public List<AppCategory> getCategoryList (String pid){
		List<AppCategory> categoryLevelList = null;
		try {
			categoryLevelList = appCategoryService.getAppCategoryListByParentId(pid==null?null:Integer.parseInt(pid));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoryLevelList;
	}
	
	/**
	 * 根据parentId查询出相应的分类级别列表
	 * @param pid
	 * @return
	 */
	@RequestMapping(value="/categorylevellist.json",method= RequestMethod.GET)
	@ResponseBody
	public List<AppCategory> getAppCategoryList (@RequestParam String pid){
		logger.debug("getAppCategoryList pid ============ " + pid);
		if(pid.equals("")) pid = null;
		return getCategoryList(pid);
	}

	@RequestMapping(value="/flatlist.json",method= RequestMethod.GET)
	@ResponseBody
	public List<DataDictionary> getAppFlatList (@RequestParam(value = "tcode") String a){
		List<DataDictionary> flatFormList = this.getDataDictionaryList(a);
		return flatFormList;
	}


	/**
	 * 跳转到APP信息审核页面
	 * @param appId
	 * @param versionId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/check",method= RequestMethod.GET)
	public String check(@RequestParam(value="aid",required=false) String appId,
					   @RequestParam(value="vid",required=false) String versionId,
					   Model model){
		AppInfo appInfo = null;
		AppVersion appVersion = null;
		try {
			appInfo = appService.getAppInfo(Integer.parseInt(appId));
			appVersion = appVersionService.getAppVersionById(Integer.parseInt(versionId));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute(appVersion);
		model.addAttribute(appInfo);
		return "backend/appcheck";
	}


	@RequestMapping(value="/appinfoadd",method=RequestMethod.GET)
	public String add(@ModelAttribute("appInfo") AppInfo appInfo){
		return "dev/appinfoadd";
	}
	@RequestMapping(value="/checksave",method= RequestMethod.POST)
	public String checkSave(AppInfo appInfo){
		logger.debug("appInfo =========== > " + appInfo.getStatus());
		try {
			if(appService.updateSatus(Long.valueOf(appInfo.getStatus()),appInfo.getId())){
				return "redirect:/manager/backend/app/list";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "backend/appcheck";
	}
	@RequestMapping(value="/apkexist.json",method=RequestMethod.GET)
	@ResponseBody
	public Object apkNameIsExit(@RequestParam String APKName){
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if(StringUtils.isEmpty(APKName)){
			resultMap.put("APKName", "empty");
		}else{
			AppInfo appInfo = null;
			try {
				appInfo = appInfoService.getAppInfo(null, APKName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(null != appInfo)
				resultMap.put("APKName", "exist");
			else
				resultMap.put("APKName", "noexist");
		}
		return JSONArray.toJSONString(resultMap);
	}
	@RequestMapping(value="/appinfoaddsave",method=RequestMethod.POST)
	public String addSave(AppInfo appInfo, HttpSession session, HttpServletRequest request,Model model,
						  @RequestParam(value="a_logoPicPath",required= false) MultipartFile attach){

		String logoPicPath =  null;
		String logoLocPath =  null;
		if (attach != null){
		if(!attach.isEmpty()){
			String path = ( "C:"+File.separator+"Users"+File.separator+"90325"+File.separator+"idea-workspace"+File.separator+"appmanager"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"static"+File.separator + "images" );

 			logger.info("uploadFile path: " + path);
			String oldFileName = attach.getOriginalFilename();//原文件名
			String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
			int filesize = 500000;
			if(attach.getSize() > filesize){//上传大小不得超过 50k
				request.setAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_4);
				return "dev/appinfoadd";
			}else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
					||prefix.equalsIgnoreCase("jepg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式
				String fileName = appInfo.getApkname() + ".jpg";//上传LOGO图片命名:apk名称.apk
				File targetFile = new File(path,oldFileName);
				if(!targetFile.exists()){
					targetFile.mkdirs();
				}
				try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					request.setAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_2);
					return "dev/appinfoadd";
				}
				logoPicPath = "/images/"+oldFileName;
				logoLocPath = path+File.separator+oldFileName; 
			}else{
				model.addAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_3);
				return "dev/appinfoadd";
			}
		}}
		try {
			AppInfo app = appInfoService.getAppInfo(null, appInfo.getApkname());
			appInfo.setStatusName(app.getStatusName());
			appInfo.setStatus(app.getStatus());
			appInfo.setId(app.getId());
			System.out.println(app.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(appInfo.getStatusName()==null ||appInfo.getStatusName()=="") {
			try {
				appInfo.setCreatedby(((DevUser)session.getAttribute("login_user")).getId());
				appInfo.setCreationdate(new Date());
				appInfo.setLogopicpath(logoPicPath);
				appInfo.setLogolocpath(logoLocPath);
				appInfo.setDevid(((DevUser)session.getAttribute("login_user")).getId());
				appInfo.setStatus(1);
				if (appInfoService.add(appInfo.getSoftwarename(), appInfo.getApkname(), appInfo.getSupportrom(), appInfo.getInterfacelanguage(), appInfo.getUpdatedate(),
						appInfo.getSoftwaresize(), appInfo.getDevid(), appInfo.getAppinfo(), appInfo.getStatus(), appInfo.getOnsaledate(), appInfo.getOffsaledate(), appInfo.getCategorylevel1(),
						appInfo.getCategorylevel2(), appInfo.getCategorylevel3(), appInfo.getDownloads(), appInfo.getFlatformid(), appInfo.getLogopicpath(), appInfo.getLogolocpath(), appInfo.getCreatedby(),
						appInfo.getCreationdate())) {
					return "redirect:/manager/backend/app/list";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (appInfo.getStatusName().equals("待审核") || appInfo.getStatusName().equals("审核未通过")){
			try {
				appInfo.setCreatedby(((DevUser)session.getAttribute("login_user")).getId());
				appInfo.setCreationdate(new Date());
				appInfo.setLogopicpath(logoPicPath);
				appInfo.setLogolocpath(logoLocPath);
				appInfo.setDevid(((DevUser)session.getAttribute("login_user")).getId());
				appInfo.setStatus(1);
				if (appInfoService.modify(appInfo.getId(),appInfo.getSoftwarename(), appInfo.getApkname(), appInfo.getSupportrom(), appInfo.getInterfacelanguage(), appInfo.getUpdatedate(),
						appInfo.getSoftwaresize(), appInfo.getDevid(), appInfo.getAppinfo(), appInfo.getStatus(), appInfo.getOnsaledate(), appInfo.getOffsaledate(), appInfo.getCategorylevel1(),
						appInfo.getCategorylevel2(), appInfo.getCategorylevel3(), appInfo.getDownloads(), appInfo.getFlatformid(), appInfo.getLogopicpath(), appInfo.getLogolocpath(), appInfo.getCreatedby(),
						appInfo.getModifyby(),appInfo.getCreationdate(),appInfo.getModifydate())){
					return "redirect:/manager/backend/app/list";
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "dev/appinfoadd";
	}
	@RequestMapping(value="/appModify")
	public String appModify(Model model,@RequestParam(value = "id")Integer id,@RequestParam(value = "status") Integer status) throws Exception {
		AppInfo appInfo = null;
		appInfo = appInfoService.getAppInfo(id,null);
		model.addAttribute("appInfo",appInfo);
		model.addAttribute("status",status);
		return  "dev/appinfomodify";
	}
	@RequestMapping(value="/appversionadd",method=RequestMethod.GET)
	public String addVersion(@RequestParam(value="id")String appId,
							 @RequestParam(value="error",required= false)String fileUploadError,
							 AppVersion appVersion,Model model){
		logger.debug("fileUploadError============> " + fileUploadError);
		if(null != fileUploadError && fileUploadError.equals("error1")){
			fileUploadError = Constants.FILEUPLOAD_ERROR_1;
		}else if(null != fileUploadError && fileUploadError.equals("error2")){
			fileUploadError	= Constants.FILEUPLOAD_ERROR_2;
		}else if(null != fileUploadError && fileUploadError.equals("error3")){
			fileUploadError = Constants.FILEUPLOAD_ERROR_3;
		}
		appVersion.setAppId((Integer.parseInt(appId)));
		List<AppVersion> appVersionList = null;
		try {
			appVersionList = appVersionService.getAppVersionList(Integer.parseInt(appId));
			appVersion.setAppName((appInfoService.getAppInfo(Integer.parseInt(appId),null)).getSoftwarename());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("appVersionList", appVersionList);
		model.addAttribute(appVersion);
		model.addAttribute("fileUploadError",fileUploadError);
		return "dev/appversionadd";
	}

	@RequestMapping(value="/addversionsave",method=RequestMethod.POST)
	public String addVersionSave(AppVersion appVersion,HttpSession session,HttpServletRequest request,
								 @RequestParam(value="a_downloadLink",required= false) MultipartFile attach ){
		String downloadLink =  null;
		String apkLocPath = null;
		String apkFileName = null;
		if(!attach.isEmpty()){
			String path = "C:"+File.separator+"Users"+File.separator+"90325"+File.separator+"idea-workspace"+File.separator+"appmanager"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"static"+File.separator + "uploadfiles";
 			logger.info("uploadFile path: " + path);
			String oldFileName = attach.getOriginalFilename();//原文件名
			String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
			if(prefix.equalsIgnoreCase("apk")){//apk文件命名：apk名称+版本号+.apk
				String apkName = null;
				try {
					
					apkName = appInfoService.getAppInfo(Integer.parseInt(appVersion.getAppId().toString()),null).getApkname();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(apkName == null || "".equals(apkName)){
					return "redirect:/manager/backend/app/appversionadd?id="+appVersion.getAppId()
							+"&error=error1";
				}
				apkFileName = apkName + "-" +appVersion.getVersionNo() + ".apk";
				File targetFile = new File(path,apkFileName);
				if(!targetFile.exists()){
					targetFile.mkdirs();
				}
				try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "redirect:/manager/backend/app/appversionadd?id="+appVersion.getAppId()
							+"&error=error2";
				}
				downloadLink = "/uploadfiles/"+apkFileName;
				apkLocPath = path+File.separator+apkFileName;
			}else{
				return "redirect:/manager/backend/app/appversionadd?id="+appVersion.getAppId()
						+"&error=error3";
			}
		}
		appVersion.setCreatedBy(Math.toIntExact(((DevUser) session.getAttribute("login_user")).getId()));
		appVersion.setCreationDate(new Date());
		appVersion.setDownloadLink(downloadLink);
		appVersion.setApkLocPath(apkLocPath);
		appVersion.setApkFileName(apkFileName);
		try {
			if(appVersionService.appsysadd(appVersion)){
				return "redirect:/manager/backend/app/list";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/manager/backend/app/appversionadd?id="+appVersion.getAppId();
	}

	//进入版本修改界面
	@RequestMapping(value="/appversionmodify",method=RequestMethod.GET)
	public String modifyAppVersion(@RequestParam("vid") String versionId,
								   @RequestParam("aid") String appId,
								   @RequestParam(value="error",required= false)String fileUploadError,
								   Model model){
		AppVersion appVersion = null;
		List<AppVersion> appVersionList = null;
		if(null != fileUploadError && fileUploadError.equals("error1")){
			fileUploadError = Constants.FILEUPLOAD_ERROR_1;
		}else if(null != fileUploadError && fileUploadError.equals("error2")){
			fileUploadError	= Constants.FILEUPLOAD_ERROR_2;
		}else if(null != fileUploadError && fileUploadError.equals("error3")){
			fileUploadError = Constants.FILEUPLOAD_ERROR_3;
		}
		try {
			appVersion = appVersionService.getAppVersionById(Integer.parseInt(versionId));
			appVersionList = appVersionService.getAppVersionList(Integer.parseInt(appId));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute(appVersion);
		model.addAttribute("appVersionList",appVersionList);
		model.addAttribute("fileUploadError",fileUploadError);
		return "dev/appversionmodify";
	}

	//保存版本的修改
	@RequestMapping(value="/appversionmodifysave",method=RequestMethod.POST)
	public String modifyAppVersionSave(AppVersion appVersion,HttpSession session,HttpServletRequest request,
									   @RequestParam(value="attach",required= false) MultipartFile attach){

		String downloadLink =  null;
		String apkLocPath = null;
		String apkFileName = null;
		if(!attach.isEmpty()){
			String path = "C:"+File.separator+"Users"+File.separator+"90325"+File.separator+"idea-workspace"+File.separator+"appmanager"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"static"+File.separator + "uploadfiles";
			logger.info("uploadFile path: " + path);
			String oldFileName = attach.getOriginalFilename();//原文件名
			String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
			if(prefix.equalsIgnoreCase("apk")){//apk文件命名：apk名称+版本号+.apk
				String apkName = null;
				try {
					apkName = appInfoService.getAppInfo(Integer.parseInt(appVersion.getAppId().toString()),null).getApkname();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(apkName == null || "".equals(apkName)){
					return "redirect:/manager/backend/app/appversionmodify?vid="+appVersion.getId()
							+"&aid="+appVersion.getAppId()
							+"&error=error1";
				}
				apkFileName = apkName + "-" +appVersion.getVersionNo() + ".apk";
				File targetFile = new File(path,apkFileName);
				if(!targetFile.exists()){
					targetFile.mkdirs();
				}
				try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "redirect:/manager/backend/app/appversionmodify?vid="+appVersion.getId()
							+"&aid="+appVersion.getAppId()
							+"&error=error2";
				}
				downloadLink = "/uploadfiles/"+apkFileName;
				apkLocPath = path+File.separator+apkFileName;
			}else{
				return "redirect:/manager/backend/app/appversionmodify?vid="+appVersion.getId()
						+"&aid="+appVersion.getAppId()
						+"&error=error3";
			}
		}
		appVersion.setModifyBy(Math.toIntExact(((DevUser) session.getAttribute("login_user")).getId()));
		appVersion.setModifyDate(new Date());
		appVersion.setDownloadLink(downloadLink);
		appVersion.setApkLocPath(apkLocPath);
		appVersion.setApkFileName(apkFileName);
		try {
			if(appVersionService.modify(appVersion)){
				return "redirect:/manager/backend/app/list";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "dev/appversionmodify";
	}

	@RequestMapping(value="/appview/{id}",method=RequestMethod.GET)
	public String view(@PathVariable String id,Model model){
		AppInfo appInfo = null;
		List<AppVersion> appVersionList = null;
		try {
			appInfo = appInfoService.getAppInfo(Integer.parseInt(id),null);
			appVersionList = appVersionService.getAppVersionList(Integer.parseInt(id));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("appVersionList", appVersionList);
		model.addAttribute(appInfo);
		return "dev/appinfoview";
	}

	@RequestMapping(value="/delapp.json")
	@ResponseBody
	public Object delApp(@RequestParam String id){
		logger.debug("delApp appId===================== "+id);
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if(StringUtils.isEmpty(id) || id==null){
			resultMap.put("delResult", "notexist");
		}else{
			try {
				if(appInfoService.appsysdeleteAppById(Integer.parseInt(id)))
					resultMap.put("delResult", "true");
				else
					resultMap.put("delResult", "false");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return JSONArray.toJSONString(resultMap);
	}
}
