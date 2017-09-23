/**
 * 弹框
 */
function dialog(url, param, dialogTitle, dialogWidth) {
    $.ajax({
        url: url,
        type: 'GET',
        data: param,
    }).done(function (data) {
        openDailog({type: 1, title: dialogTitle, content: data, area: dialogWidth});
    }).fail(function (ex) {
        console.log('弹框异常状态码:%s,异常返回内容:%s', ex.status, ex.responseText);
    });
}

/**
 * 关闭弹框
 */
function closeDialog() {
    layer.close(layer.index);
}

/**
 * 关闭所有的弹框
 */
function closeAlll() {
    layer.closeAll();
}

/**
 * 打开弹框
 * @param options
 */
function openDailog(options) {
    let defaults = {type: 0,icon: '1', title: '结果', content: '操作成功', area:'auto', yes : '', cancel: ''};
    let settings = $.extend(defaults, options);
    layer.open({
        type: settings.type,
        icon: settings.icon,
        title: settings.title,
        content: settings.content,
        area: settings.area,
        yes: function () {
            if(jQuery.isFunction(settings.yes)){
                //返回选中节点的名称和值
                settings.yes();
            }
        },
        cancel: function () {
            if(jQuery.isFunction(settings.cancel)){
                //返回选中节点的名称和值
                settings.cancel();
            }
        },
    });
}