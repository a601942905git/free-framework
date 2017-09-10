$(function(){
	${moduleName}Obj.init();
});

var ${moduleName}Obj = {
		
		//初始化
		init : function(){
			//定义基本参数
			var options = {
					//datagrid的id,如果不指定,就使用系统默认的
					//dataGridTable : '',
					//新增弹框的id,如果不指定就使用系统默认的
					//addDialogId : '',
					//新增弹框form的id,如果不指定就使用系统默认的
					//addDialogFormId : '',
					//新增form提交的url
					addSubmitFormUrl : 'web/${moduleName}/control/add${moduleName?cap_first}.do',
					//该方法主要用于在弹出新增弹框的是否初始化不属于该对象的一些数据,所以初始化信息的操作都在此执行
					addDialogInit : function(){
						
					},
					//用于处理新增表单提交之前处理操作
					beforeAddSubmit : function(){
						
					},
					//该方法主要用于修改弹框初始化该对象的数据
					modifyDialogInit : function(){
					},
					//修改弹框的id,如果不指定就使用系统默认的
					//modifyDialogId : '',
					//新增弹框form的id,如果不指定就使用系统默认的
					//modifyDialogFormId : '',
					//修改弹框form提交的url
					modifySubmitFormUrl : 'web/${moduleName}/control/modify${moduleName?cap_first}.do',
					//修改状态之前操作
					beforeModifyStatus : function(row){
					},
					//修改状态的url
					modifyStatusUrl : 'web/${moduleName}/control/modify${moduleName?cap_first}Status.do',
					//获取详情信息的url
					getDetailUrl : 'web/${moduleName}/control/get${moduleName?cap_first}Detail.do',
					//删除url
					deleteUrl : 'web/${moduleName}/control//del${moduleName?cap_first}.do',
					//删除之前操作
					beforeDeleteOperation : function(row){
					},
					//搜索查询url
					searchUrl : 'web/${moduleName}/control/get${moduleName?cap_first}List.do',
					//搜索表单id,如果不指定就使用系统默认的
					//searchFormId : '',
					//checkBusiCode : '',
			};
			//调用公用业务js初始化方法
			commonServiceObj.init(options);
			//初始化字典数据
			common.initDictionaryDetailData({pleaseSelectFlag:true});
		},
};