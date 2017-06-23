<%--
  Created by IntelliJ IDEA.
  User: NAVER
  Date: 2017-06-08
  Time: 오후 03:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>주문 내역</title>
</head>
<body>
<div>
    <c:forEach var="order" items="${orders}">
        <div class="order">
            <input type="hidden" class="order_id_hidden" value="${order.orderId}"/>
            <label>
                <c:if test="${order.status == 0}">
                    음료 요청
                </c:if>
                <c:if test="${order.status == 1}">
                    제작중
                </c:if>
                <c:if test="${order.status == 2}">
                    전달 완료
                </c:if>
            </label>
            <input type="hidden" class="order_status_hidden" value="${order.status}"/>

            <div>
                    ${order.employee.employeeName}(${order.employee.employeeNo})
                    ${order.orderYmdt}
            </div>

            <c:set var="menuCount" value="0"/>
            <c:forEach var="orderDetail" items="${order.orderDetails}">
                <div>
                    <c:if test="${orderDetail.hot}">
                        <lable class="hot_lbl">HOT</lable>
                    </c:if>
                    <c:if test="${orderDetail.hot == false}">
                        <lable class="ice_lbl">ICE</lable>
                    </c:if>
                    <label class="menu_name_lbl">
                            ${orderDetail.menu.menuName}
                            ${orderDetail.count}잔
                    </label>
                </div>
                <c:set var="menuCount" value="${menuCount+orderDetail.count}"/>
            </c:forEach>

            <div>
                <label>총 음료 ${menuCount}잔</label><br>
                <label>${order.cost}원</label>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
