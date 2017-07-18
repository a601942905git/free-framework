$(function(){
    // 页面初始化的时候加载第一个菜单下面的第一个子菜单信息
    loadFirstMenuContent();

    // 给所有的一级菜单绑定点击事件
    bindClickEventOnFirstMenu();

    // 给所有的二级菜单绑定点击事件
    bindClickEventOnSecondMenu();
});

/**
 * 给所有的二级菜单绑定点击事件
 */
function bindClickEventOnSecondMenu() {
    var $slideBarMenu = getSidebarMenu();
    $slideBarMenu.find('li[dir="second_menu"]').on('click', function () {
        $(this).parent('ul').find('li').each(function () {
            var hasActive = $(this).hasClass('active');
            if (hasActive) {
                $(this).removeClass('active');
            }
        });
        $(this).addClass('active');
        var url = $(this).find('a').attr('dir');
        loadContent(url);
        /**
         * 由于一级菜单绑定了点击事件,二级菜单也绑定了点击事件,
         * 点击二级菜单的时候回触发一级菜单的事件,所以需要阻止事件发生
         */
        event.stopPropagation();
    });
}

/**
 * 给所有的一级菜单绑定点击事件
 */
function bindClickEventOnFirstMenu() {
    var $slideBarMenu = getSidebarMenu();
    $slideBarMenu.find('li[dir="first_menu"]').on('click', function () {
        menuOperate($(this));
    });
}

/**
 * 加载菜单对应的内容
 * @param url   菜单url地址
 * @param data  菜单请求参数
 * @param fn    菜单加载成功之后的回调函数
 */
function loadContent(url, data, fn) {
    $('#main_container').load(url, data, function () {
        if (typeof(fn) == 'function') {
            fn();
        }
    });
}

/**
 * 加载第一个菜单内容
 */
function loadFirstMenuContent() {
    // 左侧菜单栏
    let $sidebarMenu = getSidebarMenu();
    // 左侧第一个菜单
    let $firstMenuLi = getFirstMenu($sidebarMenu);
    // 菜单处理操作
    menuOperate($firstMenuLi);
}

function menuOperate($firstMenuLi) {
    // 左侧一级菜单下面的子菜单容器
    let $firstMenuUl = getFirstMenuUl($firstMenuLi);
    // 左侧一级菜单下面的第一个二级菜单
    let $firstMenuFirstChildMenuLi = getFirstMenuFirstChildMenuLi($firstMenuUl);
    // 左侧一级菜单下面的第一个二级菜单下面的a标签
    let $firstMenuFirstChildMenuA = getFirstMenuFirstChildMenuA($firstMenuFirstChildMenuLi);
    // 左侧一级菜单下面的第一个二级菜单下面的a标签的url
    let url = getFirstMenuFirstChildMenuUrl($firstMenuFirstChildMenuA);
    $firstMenuLi.addClass('active');
    $firstMenuUl.css('display','block');
    $firstMenuFirstChildMenuLi.addClass('active');
    loadContent(url);
}

/**
 * 获取左侧菜单
 * @returns {*|jQuery|HTMLElement}
 */
function getSidebarMenu() {
    return $('#sidebar-menu');
}

/**
 * 获取第一个菜单
 * @param $sidebarMenu
 * @returns {T|*|{ID, TAG}|{}}
 */
function getFirstMenu($sidebarMenu) {
    return $sidebarMenu.find('ul:eq(0) li:eq(0)');
}

/**
 * 获取第一个菜单下面的ul
 * @param $fistMenu
 * @returns {T|*|{ID, TAG}|{}}
 */
function getFirstMenuUl($fistMenu) {
    return $fistMenu.find('ul:eq(0)');
}

/**
 * 获取第一个菜单的第一个子菜单li
 * @param $firstMenuUl
 * @returns {T|*|{ID, TAG}|{}}
 */
function getFirstMenuFirstChildMenuLi($firstMenuUl) {
    return $firstMenuUl.find('li:eq(0)');
}

/**
 * 获取第一个菜单的第一个子菜单
 * @param $firstMenuUl
 * @returns {T|*|{ID, TAG}|{}}
 */
function getFirstMenuFirstChildMenuA($firstMenuFirstChildMenuLi) {
    return $firstMenuFirstChildMenuLi.find('a:eq(0)');
}

/**
 * 获取第一个菜单的第一个子菜单的url
 * @param $firstMenuFirstChildMenu
 * @returns {*}
 */
function getFirstMenuFirstChildMenuUrl($firstMenuFirstChildMenuA) {
    return $firstMenuFirstChildMenuA.attr('dir');
}