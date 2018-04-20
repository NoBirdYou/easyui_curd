
var updateId;

var computer_columns=[[{
    field:'id',title:'ID',width:100,align:'center',hidden:true
},{
    field:'serialNo',title:'设备序列号',width:100,align:'center'
},{
    field:'mainboard',title:'主板',width:100,align:'center'
},{
    field:'cpu',title:'CPU',width:100,align:'center'
},{
    field:'graphics',title:'显卡',width:100,align:'center'
},{
    field:'power',title:'电源',width:100,align:'center'
}]];

/**
 * from对象转json对象
 * @returns {{}}
 */
$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    debugger;
    return o;
};

/**
 * 页面初始化
 */
function init() {



    //初始化数据表格
    $(function(){
        $('#computer_list').datagrid({
            title:'电脑基本设备列表',
            pagination:true,
            iconCls:'icon-edit',
            fit:true,
            rownumbers:true,
            fitColumns:true,
            singleSelect:true,
            idField:'id',
            columns:computer_columns
        });

        //初始化新增对话框
        $("#addWin").window({
            href:'computer_add_form.html',
            width:600,
            height:500,
            modal:true,
            closed:true
        });

        //初始化新增对话框
        $("#updateWin").window({
            href:'computer_update_form.html',
            width:600,
            height:500,
            modal:true,
            closed:true
        });

        // $("#addWin").window("close");
    });


}

/**
 * 查询数据填充到列表中
 */
function searchData() {
    var options = $("#computer_list" ).datagrid("getPager" ).data("pagination" ).options;
    var curr = options.pageNumber;
    // alert(curr);
    var size = options.pageSize;
    // $.messager.alert("提示","curr:"+curr+",size:"+size);
    ajaxLoading();
    $.ajax({
        url:"/computer/searchData.do",
        data:{},
        dataType: 'json',
        type:"POST",
        timeout : 50000, //超时时间：50秒
        success:function (data) {
            ajaxLoadEnd();
            if(data.status==0){
                var resultData=data.data;
                $('#computer_list').datagrid("loadData",resultData);
            }else{
                $.messager.alert("提示",data.message);
            }

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            ajaxLoadEnd();
            $.messager.alert("提示","请求失败！XMLHttpRequest.status："+XMLHttpRequest.status+"，XMLHttpRequest.readyState："+XMLHttpRequest.readyState);
        }
    });
}


function deleteData() {
    var selectRow=$("#computer_list").datagrid("getSelected");
    if(!selectRow){
        $.messager.alert("未选择需要删除的数据");
        return;
    }
    $.messager.confirm("提示","确定删除这条数据吗?",function (r) {
        if(r){
            ajaxLoading();
            $.ajax({
                url:"/computer/deleteData.do",
                data:{id:selectRow.id},
                dataType: 'json',
                type:"POST",
                timeout : 50000, //超时时间：50秒
                success:function (data) {
                    ajaxLoadEnd();
                    if(data.status==0){
                        $.messager.alert("提示","删除数据成功");
                        searchData();
                    }else{
                        $.messager.alert("提示",data.message);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    ajaxLoadEnd();
                    $.messager.alert("提示","请求失败！XMLHttpRequest.status："+XMLHttpRequest.status+"，XMLHttpRequest.readyState："+XMLHttpRequest.readyState);
                }
            });
        }
    });
}

function addData() {
    $("#addWin").window('open').window('refresh');
}

function closeAddDataWin(){
    $("#addWin").window('close');
}

function closeUpateDataWin(){
    $("#updateWin").window('close');
}


function updateData() {
    var selectRow=$("#computer_list").datagrid("getSelected");
    if(!selectRow){
        $.messager.alert("提示","未选择要修改的数据");
        return;
    }
    updateId=selectRow.id;
    $("#updateWin").window('open').window('refresh');
}