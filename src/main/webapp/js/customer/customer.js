/**
 * @author jun-ho.lee on 2017-06-21.
 */

$(function () {
    // HOT/ICE html 추가
    $('.menu_table > tbody').append(
        '<tr class="menu_hot_tr">' +
        '<td><label class="hot_lbl">HOT</label></td>' +
        '<td><button class="sub_btn">-</button></td>' +
        '<td><input type="text" class="menu_count_input"/></td>' +
        '<td><button class="add_btn">+</button></td>' +
        '</tr>' +
        '<tr class="menu_ice_tr">' +
        '<td><label class="ice_lbl">ICE</label></td>' +
        '<td><button class="sub_btn">-</button></td>' +
        '<td><input type="text" class="menu_count_input"/></td>' +
        '<td><button class="add_btn">+</button></td>' +
        '</tr>'
    );

    $(document).on('keydown', '.menu_count_input', function (key) {
        var keyCode = key.keyCode;
        var isNormalNumber = keyCode >= 48 && keyCode <= 57;
        var isNumberPadNumber = keyCode >= 96 && keyCode <= 105;

        // backSpace: 8, delete: 46, tab: 9
        // shift: 16, ctrl: 17, home: 35, end: 36
        // direction: 37 ~ 40(left, top, right ,down)
        var optionKeyCodes = [8, 46, 9, 16, 17, 35, 36, 37, 38, 39, 40];
        var isOptionKey = $.inArray(keyCode, optionKeyCodes) >= 0;

        return isNormalNumber || isNumberPadNumber || isOptionKey;
    });

    // '+' count++
    function countAdd(countInputVal) {
        var count = countInputVal;

        if (count == false) {
            count = 1;
        } else if ($.isNumeric(count)) {
            count++;
        } else {
            count = 0;
        }

        return count;
    }

    // '-' count--
    function countSub(countInputVal) {
        var count = countInputVal;
        if ($.isNumeric(count) && count > 0) {
            count--;
        } else {
            count = 0;
        }

        return count;
    }

    function addCountAndUpdateOrder(element) {
        var isHot = isHotRow(element);

        var countInput = $(element).closest('tr').find('.menu_count_input');
        var count = countAdd(countInput.val());
        countInput.val(count);
        updateOrderDetail(element, count, isHot);
    }

    function subCountAndUpdateOrder(element) {
        var isHot = isHotRow(element);

        var countInput = $(element).closest('tr').find('.menu_count_input');
        var count = countSub(countInput.val());
        countInput.val(count);
        updateOrderDetail(element, count, isHot);
    }

    $('.add_btn').on('click', function () {
        addCountAndUpdateOrder(this);
    })

    $('.sub_btn').on('click', function () {
        subCountAndUpdateOrder(this);
    })

    $(document).on('change', '.menu_table .menu_count_input', function () {
        var count = myParseInt($(this).val());
        if (count == 0) {
            $(this).val(count);
        }

        updateOrderDetail(this, count, isHotRow(this));
    })

    // 주문행 삭제 이벤트
    $(document).on('click', '.order_detail_remove_btn', function () {
        var thisRow = $(this).closest('tr');
        var menuCountInput = $(thisRow).find('.menu_count_input');

        menuCountInput.val(0);
        orderCountChange(menuCountInput);
        $(thisRow).remove();
    })

    // 주문 내역의 count 변경 이벤트
    $(document).on('change', '#order_detail_table_body .menu_count_input', function () {
        orderCountChange(this);
    })

    $('#order_submit_btn').on('click', function () {
        var orderArray = $('#order_detail_table_body tr');
        $(orderArray).each(function (idx, element) {
            changeParameterName(idx, element, '.menu_id_hidden');
            changeParameterName(idx, element, '.is_hot_hidden');
            changeParameterName(idx, element, '.menu_count_input');
        })

        var form = $('#order_detail_form');
        $.ajax({
            type: 'POST',
            url: '/order/confirm',
            data: form.serialize(),
            success: function (message) {
                if (confirm(message + '\n위의 사항으로 주문하시겠습니까?')) {
                    form.submit();
                } else {
                    revertParameterName('.menu_id_hidden');
                    revertParameterName('.is_hot_hidden');
                    revertParameterName('.menu_count_input');
                }
            }, error: function () {
                alert('error');
            }
        });


    })

    // update
    function updateOrderDetail(element, count, isHot) {
        var tbody = $(element).closest('tbody');
        var menuId = $(tbody).find('.menu_id_hidden').val();
        var menuCost = $(tbody).find('.menu_cost_hidden').val();
        var menuTemperature = isHot ? 'HOT' : 'ICE';

        var rowId = menuId + '_' + menuTemperature;
        if ($('#' + rowId).length <= 0) {
            var menuName = $(tbody).find('.menu_name_hidden').val();
            var newRow = '<tr id="' + rowId + '"> <input type="hidden" class="order_detail_cost_hidden" value="' + count + '"/> ' +
                '<input type="hidden" class="order_detail_total_cost_hidden" value="' + (menuCost * count) + '"/> ' +
                '<input type="hidden" class="menu_id_hidden" name="menu.menuId" value="' + menuId + '"/>' +
                '<input type="hidden" class="is_hot_hidden" name="hot" value="' + isHot + '"/>';

            var lblClassName = isHot ? 'hot_lbl' : 'ice_lbl';
            var orderTemperature = '<td><label class="' + lblClassName + '">' + menuTemperature + '</label>';
            var orderDetailMenu = '<td><label class="order_detail_menu_name">' + menuName + '</label></td>';
            var orderDetailCount = '<td><input type="text" class="menu_count_input" name="count" value="' + count + '"/></td>';
            var removeBtn = '<td><input type="button" class="order_detail_remove_btn" value="삭제"/></td>';

            newRow += orderTemperature + orderDetailMenu + orderDetailCount + removeBtn + '</tr>';
            $('#order_detail_table_body').append(newRow);
        } else {
            $('#' + rowId + ' .menu_count_input').val(count);
            $('#' + rowId + ' .order_detail_cost_hidden').val(menuCost);
            $('#' + rowId + ' .order_detail_total_cost_hidden').val(count * menuCost);
        }

        $('#payment_cost').text(calcPaymentCost());
    }

    function orderCountChange(element) {
        var count = myParseInt($(element).val());
        var cost = $(element).closest('tr').find('.order_detail_cost_hidden').val();
        $(element).closest('tr').find('.order_detail_total_cost_hidden').val(count * cost);

        $('#payment_cost').text(calcPaymentCost());
    }

    // 결제 비용 계산
    function calcPaymentCost() {
        var costArray = $('#order_detail_table_body tr .order_detail_total_cost_hidden');
        var paymentCost = 0;

        $(costArray).each(function (idx, element) {
            var cost = myParseInt($(element).val());
            if (cost == 0) {
                $(element).val(cost);
            }
            paymentCost += parseInt(cost);
        })

        return paymentCost;
    }

    function isHotRow(element) {
        var closestTr = $(element).closest('tr');
        var className = $(closestTr).prop('class');

        return className === 'menu_hot_tr';
    }

    function myParseInt(value) {
        if ($.isNumeric(value)) {
            return parseInt(value);
        } else {
            return 0;
        }
    }

    function changeParameterName(idx, element, elementName) {
        var prefix = 'orderDetails[' + idx + '].';
        var target = $(element).find(elementName);
        target.prop('name', prefix + target.prop('name'));
    }

    function revertParameterName(elementName) {
        if (elementName === '.menu_id_hidden') {
            $(elementName).prop('name', 'menu.menuId');
        }
        else if (elementName === '.is_hot_hidden') {
            $(elementName).prop('name', 'hot');
        } else if (elementName === '.menu_count_input') {
            $(elementName).prop('name', 'count');
        }
    }
})