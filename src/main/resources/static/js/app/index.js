var main = {
    init: function () {

        var _this = this;

        $('#btn-singup').on('click', function () {
            _this.save();
        });
        $('#btn-login').on('click', function () {
            _this.login();
        });
    },

    save : function () {
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
    }

};

main.init();