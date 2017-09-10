<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel" style="border:none">
                <div class="x_content">
                    <br/>
                    <table class="table" th:object="${r"${"}${moduleName}${r"}"}">
                        <tbody>
                            <#if columns??>
                            <#list columns as col>
                            <tr>
                                <td>${col.remark}:</td>
                                <td th:text="${r"*{"}${col.propertyName}${r"}"}"></td>
                                <td></td>
                                <td></td>
                            </tr>
                            </#list>
                            </#if>
                        </tbody>
                    </table>
                    <div class="ln_solid"></div>
                    <div class="form-group">
                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3" style="text-align: center">
                            <button type="button" class="btn btn-primary" th:onclick="'closeDialog()'">关闭</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</html>