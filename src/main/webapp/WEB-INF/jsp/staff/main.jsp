<%--
  Created by IntelliJ IDEA.
  User: NAVER
  Date: 2017-06-08
  Time: 오후 06:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Staff Main</title>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <style type="text/css">
        .order_list {
            display: inline-block;
        }
    </style>
</head>
<body>
<div>
    <div>
        <!-- Title Image -->
    </div>

    <div class="undone_orders">
        <label>진행중인 주문</label>
        <c:forEach var="order" items="${orderMap[false]}">
            <div class="undone_order" >
                <input type="hidden" class="order_id_hidden" value="${order.orderId}"/>
                <input type="hidden" class="order_done_hidden" value="${order.done}"/>

                <div>
                        ${order.employee.employeeName}(${order.employee.employeeNo})
                        ${order.orderYmdt}
                </div>

                <c:set var="menuCount" value="0"/>
                <c:forEach var="orderDetail" items="${order.orderDetails}">
                    <div>
                        <c:if test="${orderDetail.hot}}">
                        <lable class="hot_lbl">
                            </c:if>
                            <c:if test="${orderDetail.hot == false}}">
                            <lable class="ice_lbl">
                                </c:if>
                                    ${orderDetail.menu.menuName}
                                    ${orderDetail.count}
                            </lable>
                    </div>
                    <c:set var="menuCount" value="${menuCount+orderDetail.count}"/>
                </c:forEach>

                <div>
                    <label>총 음료 개수 ${menuCount}</label><br>
                    <label>${order.cost}원</label>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="done_orders">
        <label>완료된 주문</label>
        <c:forEach var="order" items="${orderMap[true]}">
            <div class="done_order" >
                <input type="hidden" class="order_id_hidden" value="${order.orderId}"/>
                <input type="hidden" class="order_done_hidden" value="${order.done}"/>

                <div>
                        ${order.employee.employeeName}(${order.employee.employeeNo})
                        ${order.orderYmdt}
                </div>

                <c:set var="menuCount" value="0"/>
                <c:forEach var="orderDetail" items="${order.orderDetails}">
                    <div>
                        <c:if test="${orderDetail.hot}}">
                        <lable class="hot_lbl">
                            </c:if>
                            <c:if test="${orderDetail.hot == false}}">
                            <lable class="ice_lbl">
                                </c:if>
                                    ${orderDetail.menu.menuName}
                                    ${orderDetail.count}
                            </lable>
                    </div>
                    <c:set var="menuCount" value="${menuCount+orderDetail.count}"/>
                </c:forEach>

                <div>
                    <label>총 음료 개수 ${menuCount}</label><br>
                    <label>${order.cost}원</label>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script src="/js/staff/staff.js"></script>
</body>
</html>
