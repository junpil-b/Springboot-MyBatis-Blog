$('#join--submit').on('click', function(){
			var data = {
				username:$('#username').val(),
				password:$('#password').val(),
				email:$('#email').val()
				};

			$.ajax({
				type:'POST',
				url:'/user/join',
				data:JSON.stringify(data),
				contentType:'application/json; charset=utf-8',
				dataType:'json' /* 응답 */
								
				}).done(function(r){
					if(r.statusCode == 200){
						alert('회원가입 성공');
						location.href='/user/login';
					}else{
					if(r.msg == '아이디중복'){
						alert('아이디 중복');	
				     }else{
						alert('회원가입 실패');
				 }
			    }
				}).fail(function(r){
					/* 서버 오류  */
					alert('서버 회원가입 실패');
				});
		});
