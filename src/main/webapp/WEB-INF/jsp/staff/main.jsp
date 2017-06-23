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
        .send_order {
            display: inline-block;
            margin: 20px;
        }

        .undone_order {
            display: inline-block;
            margin: 20px;
        }

        .done_order {
            display: inline-block;
            margin: 20px;
        }
    </style>
</head>
<body>
<div>
    <div>
        <!-- Title Image -->
    </div>

    <div class="send_orders">
        <div class="orders_title">
            <label>요청 받은 주문</label>
        </div>
        <c:forEach var="order" items="${orderMap['send']}">
            <div class="send_order">
                <input type="hidden" class="order_id_hidden" value="${order.orderId}"/>
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
                <br/>
                <div>
                    <label>총 음료 ${menuCount}잔</label><br>
                    <label>${order.cost}원</label>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="undone_orders">
        <div class="orders_title">
            <label>진행중인 주문</label>
        </div>
        <c:forEach var="order" items="${orderMap['receive']}">
            <div class="undone_order">
                <input type="hidden" class="order_id_hidden" value="${order.orderId}"/>
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
                <br/>
                <div>
                    <label>총 음료 ${menuCount}잔</label><br>
                    <label>${order.cost}원</label>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="done_orders">
        <div class="orders_title">
            <label>완료된 주문</label>
        </div>
        <c:forEach var="order" items="${orderMap['done']}">
            <div class="done_order">
                <input type="hidden" class="order_id_hidden" value="${order.orderId}"/>
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
                <br/>
                <div>
                    <label>총 음료 ${menuCount}잔</label><br>
                    <label>${order.cost}원</label>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script src="/js/staff/staff.js"></script>
</body>
</html>
