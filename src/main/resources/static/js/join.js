$('#join--submit').on('click', function() {
	var data = {
		username : $('#username').val(),
		password : $('#password').val(),
		email : $('#email').val()
	};

	$.ajax({
		type : 'POST',
		url : '/user/join',
		data : JSON.stringify(data),
		contentType : 'application/json; charset=utf-8',
		dataType : 'json'
	}).done(function(r) {
		if (r.statusCode == 200) {
			alert('회원가입 O');
			location.href = '/user/login';
		} else {
			if (r.msg == '아이디중복') {
				alert('아이디가 중복');
			} else {
				alert('회원가입 X');
			}
		}
	}).fail(function(r) {
		alert('서버 오류');
	});
});