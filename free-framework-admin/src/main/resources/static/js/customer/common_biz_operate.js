/**
 * 表单对象
 */
function getSubmitForm() {
    let $form = $('#form');
    return $form;
}

/**
 * form请求url
 * @returns {*}
 */
function getSubmitFormUrl() {
    let $form = getSubmitForm();
    let url = $form.attr('action');
    return url;
}

/**
 * 表单提交参数
 */
function getSubmitFormParam() {
    let $form = getSubmitForm();
    let param = $form.serialize();
    return param;
}

/**
 * 公用操作
 */
function commonOperate() {
    let url = getSubmitFormUrl();
    let param = getSubmitFormParam();
    $.ajax({
        url: url,
        data: param,
        type: 'POST',
        dataType: 'json'
    }).done(function (data) {
        commonSuccessOperate(data);
    }).fail(function (ex) {
        console.log('服务器返回错误状态码:%s,响应内容:%s', ex.status, ex.responseText);
    });
}

/**
 * 成功操作
 * @param data
 */
function commonSuccessOperate(data) {
    if (data) {
        layer.closeAll();
        if (data.code == 1){
            openDialog({
                yes: function () {
                    closeDialog();
                    refresh();
                },
                cancel: function () {
                    closeDialog();
                    refresh();
                },
            });
        } else {
            openDialog({
                icon: '2',
                content: '操作失败!'
            });
        }
    } else {
        console.log('操作信息异常%s', data);
    }
}

/**
 * 修改状态
 * @param url
 * @param id
 * @param status
 */
function updateStatus(url, id, status) {
    //询问框
    layer.confirm('确定执行该操作吗？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        $.ajax({
            url: url + "/" + id,
            data: {status: status, _method: 'PUT'},
            type: 'POST',
            dataType: 'json'
        }).done(function (data) {
            commonSuccessOperate(data);
        }).fail(function (ex) {
            console.log('服务器返回错误状态码:%s,响应内容:%s', ex.status, ex.responseText);
        });
    });
}

/**
 * 去除空格
 * @param str
 * @returns {string|*|void|XML}
 */
function trim(str) {
    let result;
    result = str.replace(/(^\s+)|(\s+$)/g, "");
    result = result.replace(/\s/g, "");
    return result;
}

/**
 * 清楚表单元素空格
 */
function clearFormTrim() {
    let $form = getSubmitForm();
    $form.find('input[clear!="false"]').each(function () {
        $(this).val(trim($(this).val()));
    });
}

