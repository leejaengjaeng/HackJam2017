<%--
  Created by IntelliJ IDEA.
  User: NAVER
  Date: 2017-06-08
  Time: 오후 06:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.hackjam.constant.MenuType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customer Main</title>
    <link rel="stylesheet" href="/style/css/bootstrap.css">
    <link rel="stylesheet" href="/style/css/bootstrap.min.css">
    <link rel="stylesheet" href="/style/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/style/css/bootstrap-vertical-grid.css">
    <link rel="stylesheet" href="/style/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/style/css/wondo.css">
</head>
<body>
<div class="wondo_container">
    <div class="top_nav_div">
        <img id="top_nav_img" src="/image/customer/top_nav.png" onclick="location.href='/customer'" style="cursor: pointer;"/>
    </div>

    <div id="cafe_info_div">
        <div id="cafe_operation_time">
            <div id="business_hour_div">
                <h3 class="time_title">Business Hour</h3>
                <h4 id="business_hour_number" class="time_number">08:50 - 18:30</h4>
            </div>

            <div id="break_time_div">
                <h3 class="time_title">Break Time</h3>
                <h4 class="time_number">10:20 - 11:20</h4>
                <h4 class="time_number">14:20 - 15:00</h4>
            </div>
        </div>

        <div id="cafe_status_div">
            <!-- 서버에서 내려주는 상태 보고 위젝 선택, 현재는 임시로 여유 -->

            <img id="cafe_status_widget_img" src="/image/customer/select_${cafeStatus}.png"/>
        </div>
    </div>


    <div>
        <div>
            <!-- 서버에서 내려주는 메뉴 목록 출력 -->
            <div class="menu_container_div">
                <div class="category_title_div">
                    <label class="category_title_lbl">Coffee</label>
                </div>
                <c:forEach var="menu" items="${menuMap[MenuType.COFFEE.name]}">
                    <table class="menu_table">
                        <tbody>
                        <tr class="menu_img_tr">
                            <td colspan="4">
                                <img class="menu_img" src="/image/customer/${menu.imageName}"/>
                            </td>
                        </tr>

                        <tr class="menu_info_tr">
                            <input type="hidden" class="menu_id_hidden" value="${menu.menuId}"/>
                            <input type="hidden" class="menu_name_hidden" value="${menu.menuName}"/>
                            <input type="hidden" class="menu_cost_hidden" value="${menu.cost}"/>
                            <td colspan="4">
                                <label class="menu_name_lbl">${menu.menuName}</label><br/>
                                <label class="menu_cost_lbl">&#8361; ${menu.cost}</label>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </c:forEach>
            </div>

            <!-- JUICE & TEA-->
            <div class="menu_container_div">
                <div class="category_title_div">
                    <label class="category_title_lbl">JUICE & TEA</label>
                </div>
                <c:forEach var="menu" items="${menuMap[MenuType.JUICE_TEA.name]}">
                    <table class="menu_table">
                        <tbody>
                        <tr class="menu_img_tr">
                            <td colspan="4">
                                <img class="menu_img" src="/image/customer/${menu.imageName}"/>
                            </td>
                        </tr>

                        <tr class="menu_info_tr">
                            <input type="hidden" class="menu_id_hidden" value="${menu.menuId}"/>
                            <input type="hidden" class="menu_name_hidden" value="${menu.menuName}"/>
                            <input type="hidden" class="menu_cost_hidden" value="${menu.cost}"/>
                            <td colspan="4">
                                <label class="menu_name_lbl">${menu.menuName}</label><br/>
                                <label class="menu_cost_lbl">&#8361; ${menu.cost}</label>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </c:forEach>
            </div>

            <!-- PREMIUM, -->
            <div class="menu_container_div">
                <div class="category_title_div">
                    <label class="category_title_lbl">Premium</label>
                </div>
                <c:forEach var="menu" items="${menuMap[MenuType.PREMIUM.name]}">
                    <table class="menu_table">
                        <tbody>
                        <tr class="menu_img_tr">
                            <td colspan="4">
                                <img class="menu_img" src="/image/customer/${menu.imageName}"/>
                            </td>
                        </tr>

                        <tr class="menu_info_tr">
                            <input type="hidden" class="menu_id_hidden" value="${menu.menuId}"/>
                            <input type="hidden" class="menu_name_hidden" value="${menu.menuName}"/>
                            <input type="hidden" class="menu_cost_hidden" value="${menu.cost}"/>
                            <td colspan="4">
                                <label class="menu_name_lbl">${menu.menuName}</label><br/>
                                <label class="menu_cost_lbl">&#8361; ${menu.cost}</label>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </c:forEach>
            </div>

            <!-- SEASON -->
            <div class="menu_container_div">
                <div class="category_title_div">
                    <label class="category_title_lbl">Season</label>
                </div>
                <c:forEach var="menu" items="${menuMap[MenuType.SEASON.name]}">
                    <table class="menu_table">
                        <tbody>
                        <tr class="menu_img_tr">
                            <td colspan="4">
                                <img class="menu_img" src="/image/customer/${menu.imageName}"/>
                            </td>
                        </tr>

                        <tr class="menu_info_tr">
                            <input type="hidden" class="menu_id_hidden" value="${menu.menuId}"/>
                            <input type="hidden" class="menu_name_hidden" value="${menu.menuName}"/>
                            <input type="hidden" class="menu_cost_hidden" value="${menu.cost}"/>
                            <td colspan="4">
                                <label class="menu_name_lbl">${menu.menuName}</label><br/>
                                <label class="menu_cost_lbl">&#8361; ${menu.cost}</label>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </c:forEach>
            </div>
        </div>
        <div class="order_detail_container_div">
            <div class="category_title_div">
                <label class="category_title_lbl">주문내역</label>
            </div>
            <div>
                <img class="order_detail_underline" src="/image/customer/order_list_underline.png">
            </div>

            <div id="order_detail_list_div">
                <form id="order_detail_form" action="/order" method="post">
                    <table id="order_detail_table">
                        <tbody id="order_detail_table_body">
                        </tbody>
                        <tfoot>
                        <tr>
                            <td></td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <label>총 결제 금액</label>
                            </td>

                            <td>
                                <label id="payment_cost"></label>원
                            </td>
                        </tr>
                        </tfoot>
                    </table>
                    <div>
                        <img class="order_detail_underline" src="/image/customer/order_list_underline.png">
                    </div>
                    <div>
                        <input type="button" class="order_btn" id="order_submit_btn" value="주문 하기"/>
                        <input type="button" class="order_btn" value="이용 내역 조회" onclick="location.href='/customer/orderHistory'"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/customer/customer.js"></script>
<script type="text/javascript" src="/js/bootstrap/bootstrap.js"></script>
</body>
</html>
