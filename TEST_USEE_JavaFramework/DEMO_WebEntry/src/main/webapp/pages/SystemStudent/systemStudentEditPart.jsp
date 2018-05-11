<%--
  User: 居泽平
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:if test="${isPage == 1}">
    <html>
    <head>
        <title>SystemStudent列表</title>
        <jsp:include page="/include/common.jsp" />
        <script type="text/javascript" src="<c:url value="/resources/lib/jquery/plugin/jquery.form.min.js" />"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/verify.js" />"></script>
    </head>
    <body>
</c:if>

<form id="myFormSystemStudent" name="" method="post" enctype="multipart/form-data">
    <table class="table-container">
        <tr>
            <td class="item-name">&nbsp;</td>
            <td class="item-value">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitEdit()">提交</a>
                <c:if test="${isPage != 1}">
                    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow('#wSystemStudent')">取消</a>
                </c:if>
            </td>
        </tr>
        <c:if test="${systemStudent != null}">
            <tr>
                <td class="item-name">编号：</td>
                <td class="item-value">
                    <p>${systemStudent.studentId}</p>
                    <input type="hidden" name="studentId" value="${systemStudent.studentId}" />
                </td>
            </tr>
        </c:if>


        <tr <c:if test="${isPage == 1}">style="display:none;"</c:if>>
            <td class="item-name">用户姓名：</td>
            <td class="item-value">
                <input type="text" name="studentName" value="${systemStudent.studentName}" />
            </td>
        </tr>



    </table>
</form>

<script type="text/javascript">
    function submitEdit(){

        $("#myFormSystemStudent").form('submit', {
            url: '<c:url value="/web/admin/systemStudent/executeSystemStudentEdit.action" />',
            onSubmit: function(){
                if(!$(this).form('validate')){
                    return false;
                }
                $.messager.progress({
                    title:'操作中',
                    msg:'正在操作。。。'
                });
                return true;
            },
            success: function(data){
                $.messager.progress('close');
                if(data == 1){
                    <c:if test="${isPage != 1}">
                    $("#wSystemStudent").window('close');
                    $('#dgSystemStudent').datagrid('reload');
                    </c:if>
                    <c:if test="${isPage == 1}">
                    $.messager.alert("提示", "修改成功", 'info');
                    </c:if>
                } else {
                    $.messager.alert("出错了", data, 'error');
                }
            }
        });
    }
</script>


<c:if test="${isPage == 1}">
    </body>
    </html>
</c:if>