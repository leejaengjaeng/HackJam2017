<%--
  Created by IntelliJ IDEA.
  User: NAVER
  Date: 2017-06-08
  Time: 오후 06:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>Staff Main</title>
    <link rel="stylesheet" href="/style/css/bootstrap.css">
    <link rel="stylesheet" href="/style/css/bootstrap.min.css">
    <link rel="stylesheet" href="/style/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/style/css/bootstrap-vertical-grid.css">
    <link rel="stylesheet" href="/style/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/style/css/wondo.css">
    <link rel="stylesheet" href="/style/css/staff.css">
</head>
<body>
<div class="wondo_container">
    <div class="top_nav_div">
        <img id="top_nav_img" src="/image/logo.png"/>
    </div>

    <div class="send_orders">
        <div class="category_title_div">
            <label class="category_title_lbl">요청 받은 주문</label>
        </div>

        <c:if test="${fn:length(orderMap['send']) == 0}">
            <div class="empty_order_div">
                <h4>
                    요청 받은 주문이 없습니다.
                </h4>
            </div>
        </c:if>

        <c:forEach var="order" items="${orderMap['send']}" varStatus="index">
            <div class="send_order">
                <button type="button" class="btn btn-warning">
                    <input type="hidden" class="order_id_hidden" value="${order.orderId}"/>
                    <input type="hidden" class="order_status_hidden" value="${order.status}"/>

                    <div class="user_div">
                            ${order.employee.employeeName}<br/>
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
                </button>
            </div>
        </c:forEach>

        <c:if test="${index==0}">
            요청받은 주문이 없습니다.
        </c:if>
    </div>

    <div class="undone_orders">
        <div class="category_title_div">
            <label class="category_title_lbl">진행중인 주문</label>
        </div>
        <c:if test="${fn:length(orderMap['receive']) == 0}">
            <div class="empty_order_div">
                <h4>
                    진행중인 주문이 없습니다.
                </h4>
            </div>
        </c:if>
        <c:forEach var="order" items="${orderMap['receive']}" varStatus="index">
            <div class="undone_order">
                <button type="button" class="btn btn-info">
                    <input type="hidden" class="order_id_hidden" value="${order.orderId}"/>
                    <input type="hidden" class="order_status_hidden" value="${order.status}"/>

                    <div class="user_div">
                            ${order.employee.employeeName}<br/>
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
                </button>
            </div>
        </c:forEach>
    </div>

    <div class="done_orders">
        <div class="category_title_div">
            <label class="category_title_lbl">완료된 주문</label>
        </div>
        <c:if test="${fn:length(orderMap['done']) == 0}">
            <div class="empty_order_div">
                <h4>
                    완료된 주문이 없습니다.
                </h4>
            </div>
        </c:if>
        <c:forEach var="order" items="${orderMap['done']}">
            <div class="done_order">
                <button type="button" class="btn btn-default">
                    <input type="hidden" class="order_id_hidden" value="${order.orderId}"/>
                    <input type="hidden" class="order_status_hidden" value="${order.status}"/>

                    <div class="user_div">
                            ${order.employee.employeeName}<br/>
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
                </button>
            </div>
        </c:forEach>
    </div>
</div>

<script type="text/javascript" src="/js/jquery.js"></script>
<script src="/js/staff/staff.js"></script>
<script type="text/javascript" src="/js/bootstrap/bootstrap.js"></script>
</body>
</html>
