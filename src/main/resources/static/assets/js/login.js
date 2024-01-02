// Google 로그인 API 초기화
function initializeGoogleSignIn() {
	gapi
		.load(
			'auth2',
			function() {
				gapi.auth2
					.init({
						client_id: '565042519356-nil3g4008pq0e0ls0hdmm4mgs56h5qq9.apps.googleusercontent.com', // 여기에 클라이언트 ID를 넣어주세요
					});
			});
}

// Google 로그인 성공 시 호출되는 함수
function onSignIn(googleUser) {
	var profile = googleUser.getBasicProfile();
	console.log('ID: ' + profile.getId());
	console.log('Name: ' + profile.getName());
	console.log('Image URL: ' + profile.getImageUrl());
	console.log('Email: ' + profile.getEmail());
}

// 문서가 로드된 후 초기화 수행
document.addEventListener('DOMContentLoaded', function() {
	initializeGoogleSignIn();
});

// 카카오톡 로그인 초기화
Kakao.init('d255d0e68a759a78133299917d445cc6');

document.addEventListener('DOMContentLoaded', function() {
	// 카카오 로그인 버튼 클릭 이벤트
	document.getElementById('kakao-login-btn').addEventListener('click',
		function() {
			Kakao.Auth.login({
				success: function(authObj) {
					console.log(authObj);
					// 로그인 성공 시 처리할 코드 추가
				},
				fail: function(err) {
					console.error(err);
					// 로그인 실패 시 처리할 코드 추가
				},
			});
		});
});

document.addEventListener('DOMContentLoaded', function() {
	// 네이버 로그인 초기화 코드
	var naverLogin = new naver.LoginWithNaverId({
		clientId: 'EBMwOMopjh_cuRmIkmFn',
		callbackUrl: 'http://localhost:8080/main',
		isPopup: true,
		loginButton: {
			color: 'white',
			type: 2,
			height: 35

		},
	});

	// 네이버 로그인 초기화 코드 호출
	naverLogin.init();
});

/*
* 아이디 찾기
*/ 
function check_find_id() {
    var email = $('#email').val().trim();
    var phone_number = $('#phone_number').val().trim();

    // 유효한 이메일 주소 형식인지 확인
    var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    // 유효한 전화번호
    var phonePattern = /^\d{3}-\d{4}-\d{4}$/;

    // 이메일 유효성 검사
    if (!emailPattern.test(email)) {
        $("#email-message").css("color", "#ec6090").text("올바른 이메일 형식으로 작성해주세용.");
        return;
    } else {
        $("#email-message").css("color", "aquamarine").text("잘 입력하셨어용!");
    }

    // 전화번호 유효성 검사
    if (!phonePattern.test(phone_number)) {
        $("#phone-message").css("color", "#ec6090").text("'-' 제외 11자리 입력해주세용.");
        return;
    } else {
        $("#phone-message").css("color", "aquamarine").text("잘 입력하셨어용!");
    }

    // 아이디 찾기 AJAX 요청
    $.ajax({
        url: '/check_find_id',
        data: {
            email: email,
            phone_number: phone_number
        },
        type: 'GET',
        dataType: 'text',
        success: function(result) {
    if (result == "불가능") {
        $("#searchid-message").css("color", "#ec6090").html("존재하는 아이디가 없어용<br>용용플레이에 가입하시는건 어때용?");
        $("#id").val('');
    } else {
        $("#searchid-message").css("color", "aquamarine").html("찾으시는 아이디는<br>" + result + "<br>입니다용");
        
    }
        }
    });
}


/*
* 비밀번호  찾기
*/ 
function check_find_pwd() {
    var id = $('#id').val().trim();
    var email = $('#email').val().trim();

    // 유효한 이메일 주소 형식인지 확인
    var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

    // 이메일 유효성 검사
    if (!emailPattern.test(email)) {
        $("#findemail-message").css("color", "#ec6090").text("올바른 이메일 형식으로 작성해주세용.");
        return;
    }

    // 아이디 유효성 검사
    if (!emailPattern.test(id)) {
        $("#findid-message").css("color", "#ec6090").text("올바른 이메일 형식으로 작성해주세용.");
        return;
    }

    // 비밀번호 찾기 AJAX 요청
    jQuery.ajax({
        url: '/check_find_pwd',
        data: {
            email: email,
            id: id
        },
        type: 'GET',
        dataType: 'text',
        success: function(result) {
            if (result == "불가능") {
                $("#searchpwd-message").css("color", "#ec6090").html("아이디 또는 메일을 확인해주세용.<br>다시 시도해주세용");
                $("#id").val('');
            } else {
				//alert(result);
                window.location.href = "/renew_pwd?id=" + result;
            }
        }
    });
}
