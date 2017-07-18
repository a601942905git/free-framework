/**
 * 弹框
 */
function dialog(url, param, dialogTitle, dialogWidth) {
    $.ajax({
        url: url,
        type: 'GET',
        data: param,
    }).done(function (data) {
        layer.open({
            type: '1',
            title: dialogTitle,
            content: data,
            area: dialogWidth,
        });
    }).fail(function (ex) {
        console.log('弹框异常状态码:%s,异常返回内容:%s', ex.status, ex.responseText);
    });
}
