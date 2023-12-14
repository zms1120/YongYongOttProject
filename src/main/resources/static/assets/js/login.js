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