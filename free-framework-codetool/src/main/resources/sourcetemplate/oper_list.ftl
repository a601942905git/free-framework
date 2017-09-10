<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib uri="/gm-fun-tag"  prefix="gm"%>
	<!-- 标题 -->
	<title><gm:textField textKey="system.default.title"/></title>
	<!-- 信息操作区域 -->
	<div>
		<!--搜索区域-->
		<div id="tb" style="padding:2px 5px;">
			<form id="searchFormId">
				<!-- 编号 -->
		        <gm:textField textKey="company.result.companyCode"></gm:textField>: <input id="companyCodeParam" class="easyui-textbox" name="companyCode" style="width:110px">
		        <!-- 名称 -->
		        <gm:textField textKey="company.result.companyDesc"></gm:textField>: <input id="companyDescParam" class="easyui-textbox" name="companyDesc" style="width:110px">
		        <!-- 公司锁定状态 -->
		        <gm:combobox widgetId="lockStatusSelect"  codeHiddenId="lockStatus" codeHiddenName="lockStatus"/>
		        <!-- 清除搜索框按钮 -->
		 		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="commonServiceObj.clear(this)">
		 			<gm:textField textKey="button.clear"></gm:textField></a>
		 		<!-- 搜索按钮 -->
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="commonServiceObj.search()">
		        	<gm:textField textKey="button.search"></gm:textField></a>
	        </form>
	   	</div>
	   	
	   	<!-- 数据展示区域 -->
		<div style="margin-left: 0px; margin-top: 20px;">
			<table id="dataGrid" class="easyui-datagrid" title="<gm:textField textKey="company.result.title"></gm:textField>" style="height: 600px;width:100%" 
			data-options="rownumbers:true,singleSelect:true,pagination:true,toolbar: '#btn',url:'web/${moduleName}/control/get${moduleName?cap_first}List.do'">
				<thead>
					<tr>
						<#if columns??>
						<#list columns as col>
						<th data-options="field:'${col.propertyName}',width:60,align:'center',"><gm:textField textKey="${col.remark!}"></gm:textField></th>
						</#list>
						</#if>
					</tr>
				</thead>
			</table>
			<!-- 功能按钮标签 -->
		    <gm:buttonGroup id="btn" style="height:auto" showSetting="dataGrid">
		    	<gm:a type="ADD" textKey="button.add" onclick="commonServiceObj.addDialog()"/>
		    	<gm:a type="MODIFY" textKey="button.modify" onclick="commonServiceObj.modifyDialog()"/>
		    	<gm:a type="DELETE" textKey="button.delete" onclick="commonServiceObj.delDailog()"/>
		    	<gm:a type="LOCK" textKey="button.stock" onclick="commonServiceObj.lockStatusDialog()"/>
		    	<gm:a type="UNLOCK" textKey="button.unstock" onclick="commonServiceObj.unlockStatusDialog()"/>
		    </gm:buttonGroup>
		</div>
	</div>
  
  	<!-- 引入新增页面 -->
  	<%@ include file="${moduleName}_add.jsp" %>
  	<!-- 引入修改页面 -->
  	<%@ include file="${moduleName}_modify.jsp" %>
  	<!-- 处理当前业务逻辑的JS文件 -->
	<script type="text/javascript" src="${'$'}{pageContext.request.contextPath}/pages/${moduleName}/js/${moduleName}.js"></script>