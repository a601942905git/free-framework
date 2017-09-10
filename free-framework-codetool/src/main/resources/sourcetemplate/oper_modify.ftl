<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<!-- 修改公司模块 -->
	<div id="modifyDialog" class="easyui-dialog" title="<gm:textField textKey="modify.dialog.title"></gm:textField>" style="width:auto;height:auto" closed="true" data-options="iconCls:'icon-add',resizable:false,modal:true">
		<div style="padding:10px 60px 20px 60px">
        	<form id="modifyDialogForm" method="post">
            	<table cellpadding="10" cellspacing="10" style="line-height: 30px;">
                	<#if columns??>
					<#list columns as col>
					<tr>
	                    <td align="right" width="120px"><gm:textField textKey="${col.remark!}"></gm:textField>:</td>
	                    <td>
	                    	<input class="easyui-textbox" type="text" id="${col.propertyName}" name="${col.propertyName}" data-options="required:true,validType:'length[1,8]'"></input>
						</td>
	                </tr>
					</#list>
					</#if>
				</table>
        	</form>
	        <div style="text-align:center;padding:5px">
	            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="commonServiceObj.submitMofifyForm()"><gm:textField textKey="add.dialog.btn.confirm"></gm:textField></a>
	            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="commonServiceObj.closeModifyDialog()"><gm:textField textKey="add.dialog.btn.cancel"></gm:textField></a>
			</div>
		</div>
	</div>