
//是否编辑过，用作datagrid编辑
var isEditing=false;


var deviceCategorys = [
    {deviceCategoryId:'memoryDevice',name:'存储设备'},
    {deviceCategoryId:'inputOutputDevice',name:'输入输出设备'},
    {deviceCategoryId:'displayDevice',name:'显示设备'},
    {deviceCategoryId:'networkDevice',name:'网络设备'}
];



/**
 * 下拉框赋值
 * @type {*[]}
 */

function initUpate() {



    /**
     * datagrid初始化
     */
    $(function(){
        $('#computer_extend_update_list').datagrid({
            title:'扩展设备',
            iconCls:'icon-edit',
            fit:true,
            rownumbers:true,
            fitColumns:true,
            singleSelect:true,
            toolbar:[{
                iconCls: 'icon-add',
                handler: function(){insert()}
            }],
            columns:[[
                {field:'deviceCategory',title:'设备类型',width:100,align:'center',
                    formatter:function(value){
                        for(var i=0; i<deviceCategorys.length; i++){
                            if (deviceCategorys[i].deviceCategoryId == value) return deviceCategorys[i].name;
                        }
                        return value;
                    },
                    editor:{
                        type:'combobox',
                        options:{
                            valueField:'deviceCategoryId',
                            textField:'name',
                            data:deviceCategorys,
                            required:true
                        }
                    }
                },
                {field:'deviceName',title:'设备名称',width:180,editor:'text',align:'center'},
                {field:'devicePrice',title:'设备单价',width:80,align:'right',editor:{type:'numberbox',options:{precision:2}},align:'center'},
                {field:'deviceNum',title:'设备数量',width:80,align:'right',editor:{type:'numberbox'},algin:'center'},
                {field:'action',title:'操作',width:80,align:'center',
                    formatter:function(value,row,index){
                        if (isEditing){
                            var s = '<a href="#" onclick="saverow(this)">保存</a> ';
                            var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
                            return s+c;
                        } else {
                            var e = '<a href="#" onclick="editrow(this)">编辑</a> ';
                            var d = '<a href="#" onclick="deleterow(this)">删除</a>';
                            return e+d;
                        }
                    }
                }
            ]],
            onBeforeEdit:function(index,row){
                isEditing=true;
                // row.editing = true;
                updateActions(index);
            },
            onAfterEdit:function(index,row){
                isEditing=false;
                // row.editing = false;
                updateActions(index);
            },
            onCancelEdit:function(index,row){
                isEditing=false;
                // row.editing = false;
                updateActions(index);
            }
        });
    });
    
    getData();

}


function updateActions(index){
    $('#computer_extend_update_list').datagrid('updateRow',{
        index: index,
        row:{}
    });
}

/**
 * 获取行索引
 * @param target
 * @returns {number}
 */
function getRowIndex(target){
    var tr = $(target).closest('tr.datagrid-row');
    return parseInt(tr.attr('datagrid-row-index'));
}

/**
 * 编辑行
 * @param target
 */
function editrow(target){
    $('#computer_extend_update_list').datagrid('beginEdit', getRowIndex(target));
}

/**
 * 删除行
 * @param target
 */
function deleterow(target){
    $.messager.confirm('Confirm','Are you sure?',function(r){
        if (r){
            $('#computer_extend_update_list').datagrid('deleteRow', getRowIndex(target));
        }
    });
}

/**
 * 保存行
 * @param target
 */
function saverow(target){
    $('#computer_extend_update_list').datagrid('endEdit', getRowIndex(target));
}

/**
 * 取消操作
 * @param target
 */
function cancelrow(target){
    $('#computer_extend_update_list').datagrid('cancelEdit', getRowIndex(target));
}

/**
 * 新增
 */
function insert(){
    var row = $('#computer_extend_update_list').datagrid('getSelected');
    if (row){
        var index = $('#computer_extend_update_list').datagrid('getRowIndex', row);
    } else {
        index = 0;
    }
    $('#computer_extend_update_list').datagrid('insertRow', {
        index: index,
        row:{

        }
    });
    $('#computer_extend_update_list').datagrid('selectRow',index);
    $('#computer_extend_update_list').datagrid('beginEdit',index);
}

/**
 * 提交数据
 */
function submitData(){
    alert("update");
    var mainInfoJsonData;
    mainInfoJsonData=$('#updateMainInfo').serializeObject();
    mainInfoJsonData['id']=updateId;
    mainInfoJsonData["extendDevices"]= $('#computer_extend_update_list').datagrid('getRows');
    delete mainInfoJsonData['editing'];
    ajaxLoading();
    $.ajax({
        url:"/computer/updateData.do",
        data:JSON.stringify(mainInfoJsonData),
        dataType: 'json',
        contentType: 'application/json',
        type:"POST",
        timeout : 50000, //超时时间：50秒
        success:function (data) {
            ajaxLoadEnd();
            if(data.status==0){
                $.messager.alert("提示",data.msg);
            }else{
                $.messager.alert("提示",data.message);
            }
            closeUpateDataWin();
            searchData();

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            ajaxLoadEnd();
            $.messager.alert("提示","请求失败！XMLHttpRequest.status："+XMLHttpRequest.status+"，XMLHttpRequest.readyState："+XMLHttpRequest.readyState);
            $("addWin").window("close");
        }
    });
}

function onAddWinOpenBefore() {
    alert("onAddWinOpenBefore");
    initAdd();
}

function getData() {
    var requestParam={};
    requestParam.id=updateId;
    ajaxLoading();
    $.ajax({
        url:"/computer/getData.do",
        data:requestParam,
        dataType: 'json',
        type:"POST",
        timeout : 50000, //超时时间：50秒
        success:function (data) {
            ajaxLoadEnd();
            if(data.status==0){
                // $.messager.alert("提示","OK");
                $("#updateMainInfo").form('load',data.data);
                debugger;
                $("#computer_extend_update_list").datagrid('loadData',data.data.extendDevices);
            }else{
                $.messager.alert("提示","ERROR");
            }
            // closeUpateDataWin();
            // searchData();

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            ajaxLoadEnd();
            $.messager.alert("提示","请求失败！XMLHttpRequest.status："+XMLHttpRequest.status+"，XMLHttpRequest.readyState："+XMLHttpRequest.readyState);
            // $("addWin").window("close");
        }
    });

}