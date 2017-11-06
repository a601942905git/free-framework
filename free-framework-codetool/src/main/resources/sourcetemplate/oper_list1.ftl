<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<div>
    <div class="row">
        <div class="col-md-12">
            <ol class="breadcrumb" style="background-color: #fff">
                <li><a href="javascript:void(0);">组织架构</a></li>
                <li><a href="javascript:void(0);" class="active">用户管理</a></li>
            </ol>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <div class="x_panel">
                <div class="x_title">
                    <h3>用户信息管理</h3>
                </div>
                <div class="x_content">
                    <!--查询数据-->
                    <form th:action="@{'/${moduleName}s/'}" class="form" id="search_form">
                        <div class="row">
							<#if columns??>
                            <#list columns as col>
                            <div class="col-xs-12 col-sm-4 col-md-2">
                                <div class="input-group">
                                    <span class="input-group-addon">${col.remark!}</span>
                                    <input class="form-control" name="${col.propertyName}" id="${col.propertyName}" th:value="${r"${"}${moduleName}Param${r"."}${col.propertyName}${r"}"}"/>
                                </div>
                            </div>
                            </#list>
							</#if>
                            <div class="col-xs-12 col-sm-4 col-md-2">
                                <button type="button" class="btn btn-info" th:onclick="'searchForm()'">
                                    <span class="glyphicon glyphicon-search"></span>
                                    搜索
                                </button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <button type="button" class="btn btn-primary" th:onclick="'dialog(\'${moduleName}s/page/add\', \'\', \'新增用户\', \'600px\')'">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                    新增
                                </button>
                            </div>
                        </div>
                    </form>

                    <!--列表数据-->
                    <table class="table table-striped table-hover table-condensed">
                        <thead>
                        <tr>
                        <#if columns??>
                        <#list columns as col>
                            <th>${col.remark!}</th>
                        </#list>
                        </#if>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
							<tr th:each="${moduleName} : ${r"${pageInfo.list}"}">
                            <#if columns??>
                            <#list columns as col>
                                <td th:text="${r"${"}${moduleName}${r"."}${col.propertyName}${r"}"}"></td>
                            </#list>
                            </#if>
								<td>
                                    <a th:onclick="'dialog(\'${moduleName}s/' + ${r"${"}${moduleName}${r"."}${primaryProperty}} + '\', \'\', \'查看用户详情\', \'600px\')'" class="btn btn-primary btn-xs">
                                        <i class="fa fa-folder"></i> 查看
                                    </a>
                                    <a th:onclick="'dialog(\'${moduleName}s/page/update\', {id:' + ${r"${"}${moduleName}${r"."}${primaryProperty}${r"}"} + '}, \'编辑用户\', \'600px\')'" class="btn btn-info btn-xs">
                                        <i class="fa fa-pencil"></i> 编辑
                                    </a>
                                    <a th:if="${r"${"}${moduleName}${r".status"} == '1'${r"}"}" th:onclick="'updateStatus(\'${moduleName}s/\', ' + ${r"${"}${moduleName}${r"."}${primaryProperty}${r"}"} + ', \'-1\')'" class="btn btn-danger btn-xs">
                                        <i class="fa fa-folder"></i> 停用
                                    </a>
                                    <a th:if="${r"${"}${moduleName}${r".status"} == '-1'${r"}"}" th:onclick="'updateStatus(\'${moduleName}s/\', ' + ${r"${"}${moduleName}${r"."}${primaryProperty}${r"}"} + ', \'1\')'" class="btn btn-danger btn-xs">
                                        <i class="fa fa-folder"></i> 启用
                                    </a>
								</td>
							</tr>
                        </tbody>
                    </table>
                    <!--分页-->
                    <div th:replace="common/common_page::page"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</html>