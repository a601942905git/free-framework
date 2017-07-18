let pageNo = 1;
let pageSize = 15;

/**
 * 刷新页面
 */
function refresh() {
    var url = getSearchFormUrl();
    var param = getFormParam();
    $('#main_container').load(url, param);
}

/**
 * 表单对象
 */
function getSearchForm() {
    var $form = $('#search_form');
    return $form;
}

/**
 * form请求url
 * @returns {*}
 */
function getSearchFormUrl() {
    var $form = getSearchForm();
    var url = $form.attr('action');
    return url;
}

/**
 * 搜索
 */
function searchForm() {
    pageNo = 1;
    pageSize = $("#pageSize").val();
    refresh();
}


/**
 * 清楚表单
 */
function clearForm() {
    $('#search_form')[0].reset();
}

/**
 * 分页
 */
function searchPage(pageNoParam, pageSizeParam) {
    pageNo = pageNoParam;
    pageSize = pageSizeParam;
    refresh();
}

/**
 * form提交参数信息
 * @returns {*|jQuery}
 */
function getFormParam() {
    let formParam = getSearchForm().serialize();
    formParam += "&pageNo=" + pageNo;
    formParam += "&pageSize=" + pageSize;
    return formParam;
}

/**
 * 修改页码大小
 */
function changePage(obj) {
    pageNo = 1;
    pageSize = $(obj).find('option:selected').val();
    refresh();
}
