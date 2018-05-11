<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/10
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form id="myFormSystemStudent" name="" action="<c:url value="/web/admin/systemStudent/executeSystemStudentEdit.action" />" method="post">
    <table class="table-container">
        <tr>
            <td class="item-name">&nbsp;</td>
            <td class="item-value">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow('#wSystemStudent')">关闭</a>
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

        <tr>
            <td class="item-name">用户姓名：</td>
            <td class="item-value">
                ${systemStudent.studentName}
            </td>
        </tr>

    </table>
</form>
