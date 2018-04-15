let zTreeObj;

$(function () {
    initTree();
});

function initTree() {
    let setting = {
        view: {
            addHoverDom: false,
            removeHoverDom: false,
            selectedMulti: false,
        },
        check: {
            enable: true,
        },
        data: {
            simpleData: {
                enable: true
            }
        },
    };

    let zNodes = JSON.parse($('#tree_data').val());
    zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
}

/**
 * 获取选中的结点信息
 */
function getCheckNodes() {
    // 定义选中节点数组,里面包含全选中和半选中的节点
    let checkNodeData = new Array();
    // 获取节点选中状态
    let checkedNodes = zTreeObj.getCheckedNodes(true);
    for(let i = 0,len = checkedNodes.length;i < len;i++){
        if (checkedNodes[i].id == 0){
            continue;
        }

        // 获取选中的状态
        let checkStatus = checkedNodes[i].getCheckStatus();
        // 如果是全选状态
        if(!checkStatus.half){
            // 全选
            let allCheckedData = {"resourceId":checkedNodes[i].id,checked:"1"};
            checkNodeData.push(allCheckedData);
        }else{
            // 半选
            let halfCheckedData = {"resourceId":checkedNodes[i].id,checked:"-1"};
            checkNodeData.push(halfCheckedData);
        }
    }
    return checkNodeData;
}

/**
 * 提交角色权限
 */
function submitRoleAuthority() {
    let checkNodeData = getCheckNodes();
    if (checkNodeData || checkNodeData.length == 0){
        showMsgTip({message: '请选择相应的权限', icon: '2'});
        return;
    }
    closeDialog();
}