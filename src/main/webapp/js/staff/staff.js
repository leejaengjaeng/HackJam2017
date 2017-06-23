/**
 * @author jun-ho.lee on 2017-06-22.
 */

$(function () {
    $(document).on('click', '.send_order', function () {
        if (confirm('주문을 받으시겠습니까?')) {
            changeOrder(this, 1);
        }
    });

    $(document).on('click', '.undone_order', function () {
        if (confirm('주문을 완료 처리하시겠습니까?')) {
            changeOrder(this, 2);
        }
    });

    $(document).on('click', '.done_order', function () {
        if (confirm('완료된 주문을 취소 처리하시겠습니까?')) {
            changeOrder(this, 1);
        }
    });

    function changeOrder(element, changeStatus) {
        var order = $(element);
        var orderId = order.find('.order_id_hidden').val();

        $.ajax({
            type: 'POST',
            url: '/order/changeStatus',
            data: {
                'orderId': orderId,
                'status': changeStatus
            }, success: function () {
                var moveClass
                var html;

                order.find('.order_status_hidden').val(changeStatus);

                if(changeStatus == 1){
                    moveClass = '.undone_orders';
                    html = '<div class="undone_order">'+order.html()+'</div>';
                } else{
                    moveClass = '.done_orders';
                    html = '<div class="done_order">'+order.html()+'</div>';
                }

                $(moveClass).append(html);
                order.remove();
            }, error: function () {
                alert('요청이 실패하였습니다. 잠시 후 다시 시도하세요');
            }
        })
    }
});