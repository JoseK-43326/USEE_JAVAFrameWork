<%--
  User: 居泽平
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SystemStudent列表</title>
    <jsp:include page="/include/common.jsp" />
    <script type="text/javascript" src="<c:url value="/resources/lib/jquery/plugin/jquery.form.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/verify.js" />"></script>
    <script type="text/javascript">

        $(document).ready(function(){
            $('#dgSystemStudent').datagrid({
                url:'<c:url value="/web/admin/systemStudent/getSystemStudentListJSON.json" />',
                title:'系统用户',
                singleSelect:true,
                fit:true,
                fitColumns:true,
                toolbar:'#tbSystemStudent',
                rownumbers:true,
                pagination:true,
                pageSize: 20,
                columns:[[
                    {field:'studentId',title:'主键编号',align:'center'},
                    {field:'studentName',title:'用户姓名',width:8,align:'center'},
                    {field:'edit',title:'Edit',align:'center',formatter:function(val,row){
                            if(row.studentId < 0) { return ''; }
                            var viewUrl = "<c:url value="/web/admin/systemStudent/getSystemStudentViewPage.action?studentId=" />" + row.studentId;
                            var editUrl = "<c:url value="/web/admin/systemStudent/getSystemStudentEditPage.action?studentId=" />" + row.studentId;
                            return '&nbsp;' +
                                //'<a href="javascript:void(0)" onclick="openWindow(\'#wSystemUser\', \'' + viewUrl + '\')">详情</a>' +
                                //'&nbsp;|&nbsp;' +
                                '<a href="javascript:void(0)" onclick="openWindow(\'#wSystemStudent\', \'' + editUrl + '\')">编辑</a>' +
                                //'&nbsp;|&nbsp;' +
                                //'<a href="javascript:void(0)" onclick="submitRemove(\'' + row.userId + '\', \'1\')">假删除</a>' +
                                '&nbsp;|&nbsp;' +
                                '<a href="javascript:void(0)" onclick="submitRemove(\'' + row.studentId + '\', \'0\')">删除</a>' +
                                '&nbsp;';
                        }}
                ]]
            });

            formatPagination("dgSystemStudent");

            vEasyUIUtil.createWindow("wSystemStudent", {
                title: '新增/编辑',
                width:640,
                height:vSugar.getMaxWinHeight("mainPanel", 600),
                modal:true,
                closed:true,
                iconCls:'icon-save'
            });
        });

        /******************************* 数据操作 *******************************/

        function submitRemove(studentId, isFakeDelete){
            if (!confirm("确认删除")){
                return;
            }
            // isFakeDelete=1表示假删除
            $.post("logicRemoveSystemStudent.action", {studentId: studentId, isFakeDelete: isFakeDelete},
                function(result){
                    if(result == 1){
                        $('#dgSystemStudent').datagrid('reload');
                    }
                }
            );
        }

        function submitSearch(){
            var dgSystemStudent = $('#dgSystemStudent');

            dgSystemStudent.datagrid('options').pageNumber = 1;
            dgSystemStudent.datagrid('getPager').pagination("options").pageNumber = 1;
            var param = {
            };
            $("#dgSystemStudent").datagrid({queryParams: param}, 'reload');
        }

        function clearSearch(){
        }
    </script>
</head>
<body class="easyui-layout">

<div data-options="region:'center',border:false,style:{padding:'4px'}" class="mainPanel">
    <div id="tbSystemStudent" class="toolBarPadding">
        <div style="float:left;">
            <a href="javascript:void(0)" class="easyui-linkbutton v-align-middle" data-options="iconCls:'icon-add',plain:true"
               onclick="openWindow('#wSystemStudent', '<c:url value="/web/admin/systemStudent/getSystemStudentEditPage.action" />')">新增&nbsp;</a>
        </div>
        <div style="float:right;">
        </div>
        <div style="clear:both;"></div>
    </div>
    <table id="dgSystemStudent"></table>
</div>

</body>
</html>