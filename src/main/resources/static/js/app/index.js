var main = {
    init: function () {

        var _this = this;

        $('#btn-singup').on('click', function () {
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
        })

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
        }).done(function() {
            alert('가입이 완료되었습니다.');
            window.location.href='/';
        }).fail(function(error){
            alert(JSON.stringify(error));
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
            title: $('#title').val(),
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
    //장바구니 담기

    //구매하기
};

main.init();