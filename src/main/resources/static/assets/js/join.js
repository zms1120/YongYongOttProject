/**
 * 
 */

//페이지 로드 시 가입일만 자동으로 계산
window.onload = function() {
	calculateRegDate();
	
}

	document.getElementById('password').addEventListener('input', function () {
		const passwordInput = document.getElementById('password');
		const passwordCkInput = document.getElementById('password_ck');

		if (passwordInput.value.length > 0) {
			passwordCkInput.style.display = 'block'; // 비밀번호가 입력되면 password_ck를 보이도록 변경
		} else {
			passwordCkInput.style.display = 'none'; // 비밀번호가 없으면 password_ck를 숨기도록 변경
		}
	});


function calculateRegDate() { // 페이지 로드되면 바로 가입일은 현재 날짜로 작성됨
	var today = new Date();
	var regDateInput = document.getElementById("reg_date");
	regDateInput.value = formatDate(today);
}

// 날짜를 "yyyy-MM-dd" 형식으로 변환
function formatDate(date) {

	var year = date.getFullYear();
	var month = (date.getMonth() + 1).toString().padStart(2, '0');
	var day = date.getDate().toString().padStart(2, '0');
	return year + "-" + month + "-" + day;
}

function selectDate() { // 옵션에 따라 갱신일과 종료일이 다름
	// 가입일 가져오기
	var regDate = document.getElementById("reg_date").value;

	if (regDate) {
		var regDateObj = new Date(regDate);
		var position = document.getElementById("position").value;
		var renewDateObj = new Date(regDateObj);
		var endDateObj = new Date(regDateObj);

		// 이용권 갱신날짜 계산 (방문자는 8일, 나머지는 31일)
		if (position === "방문자") {
			renewDateObj.setDate(regDateObj.getDate() + 8);
		} else {
			renewDateObj.setDate(regDateObj.getDate() + 31);
		}

		// 이용권 종료날짜 계산 (방문자는 7일, 나머지는 30일)
		if (position === "방문자") {
			endDateObj.setDate(regDateObj.getDate() + 7);
		} else {
			endDateObj.setDate(regDateObj.getDate() + 30);
		}

		// 각 입력 날짜 설정
		document.getElementById("renew_date").value = formatDate(renewDateObj);
		document.getElementById("end_date").value = formatDate(endDateObj);
	}
}

// 회원가입시, 필수입력 항목 확인
function go_save() {
	var id = document.getElementById("id");
	var password = document.getElementById("password");
	var password_ck = document.getElementById("password_ck");
	var name = document.getElementById("name");
	var phone_number = document.getElementById("phone_number");
	var email = document.getElementById("email");
	var renew_date = document.getElementById("renew_date");

	// 비밀번호가 대문자, 소문자, 숫자, 특수문자를 최소한 1개씩 포함하고 8글자 이상인지 확인
	var passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,16}$/;


	if (id.value == "" || id.length == 0) {
		alert("아이디를 입력해주세요.")
		id.focus();
		return false;
	} else if (password.value == "" || password.length == 0) {
		alert("비밀번호를 입력해주세요.")
		password.focus();
		return false;
	} else if (!passwordRegex.test(password.value)) {
		alert("비밀번호는 대문자, 소문자, 숫자, 특수문자를 최소한 1개씩 포함하고 8글자 이상이어야 합니다.");
		password.focus();
		return false;
	} else if (document.getElementById("password_ck").value != document.getElementById("password").value) {
		alert("비밀번호가 일치하지 않습니다..")
		password_ck.focus();
		return false;
	} else if (name.value == "" || name.length == 0) {
		alert("이름 입력해주세요.")
		name.focus();
		return false;
	} else if (phone_number.value == "" || phone_number.length == 0) {
		alert("핸드폰 번호를 입력해주세요.")
		phone_number.focus();
		return false;
	} else if (email.value == "" || email.length == 0) {
		alert("이메일을 입력해주세요.")
		email.focus();
		return false;
	} else if (renew_date.value == "" || renew_date.length == 0) {
		alert("날짜 계산 버튼을 입력해주세요.")
		renew_date.focus();
		return false;
	} else {
		return true;
	}
}

