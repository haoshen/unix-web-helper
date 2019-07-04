function bind() {
	$('#btn-verify').click(function () {
        $.ajax({
            type: "post",
            url: "unix/check",
            data: {
                password: $('#unix-passwd').val()
            },
            async: false,
            success: function (response) {
            	if (response == true) {
            		$('#verify_div').hide();
            		$('#test_div').show();
            		$('#file_div').show();
            	} else {
            		alert("password error");
            	}
            }
        });
    });
    $('#btn-load').click(function () {
        $.ajax({
            type: "post",
            url: "unix/load",
            data: {
            	password: $('#unix-passwd').val(),
                path: $('#file-path').val()
            },
            async: false,
            success: function (response) {
                $('#file-text').val(response);
            }
        });
    });

    $('#btn-update').click(function () {
        $.ajax({
            type: "post",
            url: "unix/update",
            data: {
            	password: $('#unix-passwd').val(),
                path: $('#file-path').val(),
                text: $('#file-text').val()
            },
            async: false,
            success: function (response) {
                alert(response);
            }
        });
    });

    $('#unix_cmd').on("keypress", function (e) {
        if(event.keyCode == 13) {
            var input = $('#unix_cmd').val();
            if (input == "") {
                return;
            }
            $.ajax({
                type: "post",
                url: "unix/test",
                data: {
                	password: $('#unix-passwd').val(),
                    cmd: encodeURIComponent(input)
                },
                success: function (response) {
                    if (response != null) {
                        $('#unix_result').val(response);
                    } else {
                        alert(response);
                    }
                }
            });
        }
    });

}

// 登录绑定
function init() {
    bind();
}


$(function () {
    init();
});