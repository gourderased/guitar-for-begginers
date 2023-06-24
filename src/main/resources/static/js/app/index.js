var main = {
    init: function () {
        var _this = this;
        $('#btn-signup').on('click', function () {
            _this.signup();
        });
        $('#btn-login').on('click', function () {
            _this.login();
        });
        $('#btn-Member-delete').on('click', function () {
            _this.memberDelete();
        });
        $('#btn-board-delete').on('click', function() {
            _this.boardDelete();
        });
        $('#btn-board-save').on('click', function() {
            _this.boardSave();
        });
        $('#btn-modify').on('click', function() {
            _this.memberModify();
        });
        $(document).on('click', '.btn-add-cart', function() {
            _this.addCart(this);
        });
        $('#btn-payment').on('click', function() {
            _this.processPayment();
        });

    },
    // 결제하기 버튼
    processPayment: function () {
        var memberId = $('#memberId').val();
        var cartItems = $('.card');
        var paymentReqList = [];

        cartItems.each(function () {
            console.log(cartItems);
            var card = $(this);
            var productId = card.find('input[type="hidden"]').val(); // productId 가져오기
            var price = parseInt(card.find('.card-text:contains("Price")').text().split(':')[1].trim()); // price 가져오기
            var quantity = parseInt(card.find('.card-text:contains("Quantity") span').text()); // quantity 가져오기
            console.log(card,productId,price,quantity);

            var paymentReq = {
                memberId: memberId,
                productId: productId,
                price: price,
                quantity: quantity
            };
            paymentReqList.push(paymentReq);
        });
        var data= paymentReqList

        $.ajax({
            type: 'POST',
            url: '/cart/payment',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (response) {
            if(response.isSuccess) {
                alert('결제가 완료되었습니다.');
                window.location.href='/';
            }
            else {
                alert(response.message);
            }
        }).fail(function (error) {
            alert('결제에 실패했습니다. 다시 시도해주세요.');

        });
    },


    //장바구니 담기
    addCart : function (btn) {
        var memberId = $(btn).data('member-id');

        if (memberId === '0') {
            alert('로그인이 필요합니다.');
            return;
        }
        var productId = $(btn).data('product-id');

        var data = {
            memberId: memberId,
            productId: productId
        };
        $.ajax({
            type: 'POST',
            url: '/cart/create',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('장바구니에 담겼습니다.');
        }).fail(function (error) {
            alert('로그인이 필요합니다.');
        });
    },

    //회원가입
    signup : function () {
        var data = {
            loginId: $('#inputId').val(),
            password: $('#inputPw').val(),
            email: $('#inputEmail').val()
        };
        $.ajax({
            type: 'POST',
            url: '/member/create',
            datatype: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(response) {
            if(response.isSuccess) {
                alert("가입완료되었습니다.");
                window.location.href='/';
            } else {
                alert(response.message);
            }
        }).fail(function(){
            alert("요청이 실패하였습니다.");
        });
    },
    //로그인
    login : function () {
        var data = {
            loginId: $('#inputId').val(),
            password: $('#inputPw').val(),
        };
        $.ajax({
            type: 'POST',
            url: '/member/log-in',
            datatype: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('환영합니다.');
            window.location.href='/';

        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    //회원 정보 수정
    memberModify :function() {
        var id = $('#memberId').val();
        var data = {
            email: $('#inputEmail').val(),
        };

        $.ajax({
            type: 'PATCH',
            url: '/member/modify/' + id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('수정이 완료되었습니다.');
            window.location.href='/member/my-info';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    //관리자 - 회원 삭제
    memberDelete : function () {
        var id = $('#inputIndex').val();
        var data ={}
        $.ajax({
            type: 'DELETE',
            url: 'member/delete/' + id,
            datatype: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('삭제가 완료되었습니다.');
            window.location.href='/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    //관리자 - 게시글 삭제
    boardDelete : function () {
        var id = $('#inputIndex').val();
        var data ={}
        $.ajax({
            type: 'DELETE',
            url: 'board/delete/' + id,
            datatype: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('삭제가 완료되었습니다.');
            window.location.href='/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    //게시글 등록
    boardSave : function () {
        var data = {
            content: $('#content').val(),
            memberId: $('#memberId').val()
        };
        $.ajax({
            type: 'POST',
            url: '/board/create',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

$(document).ready(function() {
    main.init();
});

function decreaseQuantity(productId) {
    var quantityElement = document.getElementById("quantity-" + productId);
    var currentQuantity = parseInt(quantityElement.innerHTML);
    if (currentQuantity > 1) {
        quantityElement.innerHTML = currentQuantity - 1;
    }
}

function increaseQuantity(productId) {
    var quantityElement = document.getElementById("quantity-" + productId);
    var currentQuantity = parseInt(quantityElement.innerHTML);
    quantityElement.innerHTML = currentQuantity + 1;
}