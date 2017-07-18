/**
 * 表单对象
 */
function getSubmitForm() {
    var $form = $('#form');
    return $form;
}

/**
 * form请求url
 * @returns {*}
 */
function getSubmitFormUrl() {
    var $form = getSubmitForm();
    var url = $form.attr('action');
    return url;
}

/**
 * 表单提交参数
 */
function getSubmitFormParam() {
    var $form = getSubmitForm();
    var param = $form.serialize();
    return param;
}

/**
 * 公用操作
 */
function commonOperate() {
    var url = getSubmitFormUrl();
    var param = getSubmitFormParam();
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
        layer.close(layer.index);
        if (data == 1){
            layer.open({
                icon: '1',
                title: '结果',
                content: '操作成功!',
                yes: function () {
                    layer.close(layer.index);
                    refresh();
                },
                cancel: function () {
                    layer.close(layer.index);
                    refresh();
                },
            });
        } else {
            layer.open({
                icon: '2',
                title: '结果',
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
            url: url,
            data: {id: id, status: status, _method: 'PUT'},
            type: 'POST',
            dataType: 'json'
        }).done(function (data) {
            commonSuccessOperate(data);
        }).fail(function (ex) {
            console.log('服务器返回错误状态码:%s,响应内容:%s', ex.status, ex.responseText);
        });
    });
}

