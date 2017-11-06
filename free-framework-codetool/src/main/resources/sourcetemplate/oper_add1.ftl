<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel" style="border:none">
                    <div class="x_content">
                        <br/>
                        <form th:action="@{'/${moduleName}s/'}" id="form" type="post" class="form-horizontal form-label-left">
                            <#if columns??>
                            <#list columns as col>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="${col.propertyName}">${col.remark!}: <span>*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="${col.propertyName}" name="${col.propertyName}" class="form-control col-md-7 col-xs-12"/>
                                </div>
                            </div>
                            </#list>
                            </#if>
                            <div class="ln_solid"></div>
                            <div class="form-group">
                                <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3" style="text-align: center">
                                    <button type="submit" class="btn btn-primary">新增</button>
                                    <button class="btn btn-success" type="reset">重置</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</html>