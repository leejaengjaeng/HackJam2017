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
    <script type="text/javascript" src="/js/jquery.js"></script>
    <style type="text/css">
        .menu_table {
            display: inline-block;
            margin: 10px;
        }

        td {
            text-align: center;
        }

        .ice_lbl {
            color: blue;
            font-weight: bold;
        }

        .hot_lbl {
            color: red;
            font-weight: bold;
        }

        .menu_count_input {
            ime-mode: disabled;
            text-align: center;
        }
    </style>
</head>
<body>
<div id="top_nav_div">
    <img id="top_nav_img" src="/image/customer/top_nav.png"/>
</div>

<div id="cafe_info_div">
    <div id="business_hour_div">
        <label id="business_hour_title_lbl">Business Hour</label>
        <label id="business_hour_content_lbl">8:50 - 18:30</label>
    </div>

    <div id="break_time_div">
        <label id="break_time_title_lbl">Break Time</label>
        <label id="break_time_content_lbl1">10:20 - 11:20</label>
        <label id="break_time_content_lbl2">14:20 - 15:00</label>
    </div>

    <div>
        <!-- 서버에서 내려주는 상태 보고 위젝 선택, 현재는 임시로 여유 -->

        <img id="cafe_status_widget_img" src="/image/customer/widget_${cafeStatus}.png"/>
    </div>
</div>

<div>
    <div>
        <!-- 서버에서 내려주는 메뉴 목록 출력 -->
        <div class="menu_container">
            <div class="menu_title_div">
                <label class="menu_title_lbl">Coffee</label>
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

        <div class="menu_container_div">
            <div class="menu_title_div">
                <label class="menu_title_lbl">JUICE & TEA</label>
            </div>
            <c:forEach var="menu" items="${menuMap[MenuType.JUICE_TEA.name]}">
                <table class="menu_table">
                    <tbody>
                    <tr class="menu_img_tr">
                        <td colspan="4">
                            <img class="menu_img" src="/image/${menu.imageName}"/>
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

        <div class="order_detail_container_div">
            <div class="order_detail_title_div">
                <label>주문내역</label>
            </div>

            <div id="order_detail_list_div">
                <form id="order_detail_form" action="/order" method="post">
                    <table id="order_detail_table">
                        <tbody id="order_detail_table_body">
                        </tbody>
                        <tfoot>
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
                        <input type="button" id="order_submit_btn" value="주문 하기"/>
                        <input type="button" value="이용 내역 조회" onclick="location.href='/customer/orderHistory'"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="/js/customer/customer.js"></script>
</body>
</html>
