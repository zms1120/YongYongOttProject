
/*
// 아이디 중복 확인
function checkDuplicateId() {
	var id = document.getElementById("id").value;
	var sendform = document.getElementById("fr");

	sendform.action = "/check_find_id?id=" + id;
	sendform.mothod = "GET";
	sendform.submit();
}
*/

/*
*** STEP1 ***
*/
let id_checker = 0;
let pwd_checker = 0;
let pwdck_checker = 0;

//email 입력시 확인
$(document).ready(function() {
	$('#id').on('focusout', function(e) {
		e.preventDefault();
		
		var email = $('#id').val().trim();
		
		// 유효한 이메일 주소 형식인지 확인
        var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        if (!emailPattern.test(email)) {
            $("#id-message").css("color", "#ec6090").text("올바른 이메일 형식으로 작성해주세요.");
            id_checker = 0;
            return;
        } else(
			$.ajax({
			url: '/check_id',
			data: {
				id: email
			},
			type: 'GET',
			dataType: 'text',
			success: function(result) {
				if (result == "사용가능") {
					$("#id-message").css("color", "aquamarine").text("사용 가능한 이메일입니다.");
					id_checker = 1;
				} else if (result == "불가능") {
					$("#id-message").css("color", "#ec6090").text("사용할 수 없는 이메일입니다.");
					id_checker = 0;
				}
			  }
			  
			})
		)
	
	});
});

// 비밀번호 확인
$(document).ready(function() {

	$('#password').on('focusout', function() {
		var password = $('#password').val();
		// 숫자, 특수문자 각각을 포함하는지 확인
		const numberRegex = /[0-9]/;
		const specialCharRegex = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/;
		
		// 비밀번호 길이가 8-20자인지 확인
		if (password == "") {
			return;
		} else if (
			password.length < 8 || password.length > 20 ||
			!numberRegex.test(password) ||
			!specialCharRegex.test(password)
		) {
			$('#pwd-message').css("color", "#ec6090").text("비밀번호 형식에 맞게 입력해주세요");
			$('#pwd-checker').text("0");
			pwd_checker = 0;
		} else {
			$('#pwd-message').css("color", "aquamarine").text("확인되었습니다.");
			$('#pwd-checker').text("1");
			pwd_checker = 1;
		}
	});
	
	$('#password').on('focus', function() {
		$('#pwd-message').css("color", "#f7f7f7").text("비밀번호는 8-20자 이내로 영문 대소문자, 숫자, 특수문자를 반드시 하나씩 포함하여 입력해주세요.");
	});

	$('#password_ck').on('focusout', function() {
		var passwordInput = $('#password').val();
		var passwordCkInput = $('#password_ck').val();

		if(passwordCkInput == ""){
			pwdck_checker = 0;
			return;
		} else if (passwordInput == passwordCkInput) {
			$('#pwdck-message').css("color", "aquamarine").text("확인되었습니다.");
			pwdck_checker = 1;
		} else {
			$('#pwdck-message').css("color", "#ec6090").text("비밀번호가 다릅니다.");
			pwdck_checker = 0;
		}
	});
	
});

/*
*** STEP2 ***
*/
let number = 0;
function sendMail() {
	// 이메일 인증
	var email = $('#id').val().trim();
	//alert(email);
	
	$.ajax({
		url: '/email_auth',
		data: {
			email: email
		},
		type: 'post',
		dataType: 'text',
		success: function(result) {
			number = result
		}
	});
}

// 입력한 인증번호 확인
let auth_checker = 0;
$(document).ready(function() {
	$(document).on('click', '.auth-button', function(e) {
		e.preventDefault();
		var user_input = $("#auth_num").val();
		
		//alert("user_input = " + user_input + "number = " + number);
	
		if (user_input == number) {
			$('.auth-message').css("color", "aquamarine").text("인증되었습니다.");
			auth_checker = 1;
			
		} else {
			$('.auth-message').css("color", "#ec6090").text("번호가 다릅니다.");
			auth_checker = 0;
		}
	});
});

/*
*** STEP3 ***
*/
$('#phone_number').on('input', function(e) {
    let inputValue = e.target.value.replace(/[^0-9]/g, ''); // 입력된 값 중 숫자만 남기기

    if (inputValue.length <= 11) {
        inputValue = inputValue.replace(/(\d{0,3})(\d{0,4})(\d{0,4})/, '$1-$2-$3'); // 전화번호 형식에 맞게 변환
    } else if (inputValue.length > 11) {
        inputValue = inputValue.substring(0, 11).replace(/(\d{0,3})(\d{0,4})(\d{0,4})/, '$1-$2-$3'); 
    }

    e.target.value = inputValue.trim(); // 변환된 값으로 입력란 업데이트
});


// next를 누를 때마다 다른 div로 넘어감
$(document).ready(function() {
	$('#next-to-step2').on('click', function(e) {
		e.preventDefault();
		//alert("id-checker = "  + id_checker + " pwd-checker = " + pwd_checker + " pwdck-checker = " + pwdck_checker)
		
		if(id_checker == 1 
			&& pwd_checker == 1
			&& pwdck_checker == 1){
			$('.step1').hide();
			$('.step2').show();
			
			sendMail();
		} else{
			return;
		}
	});
	
	$('#next-to-step3').on('click', function(e) {
		e.preventDefault();
		
		if(auth_checker == 1){
			$('.step2').hide();
			$('.step3').show();
		} else{
			return;
		}
	});
	
	$('#next-to-step4').on('click', function(e) {
		e.preventDefault();
		$('.step3').hide();
		$('.step4').show();
	});
	
	$('#submit-button').on('click', function(e) {
		e.preventDefault();
		
		if ($('#age-restriction').checked
			&& $('#service').checked
			&& $('#member_privacy').checked
			&& $('#member_payment').checked) {
				
			var sendform = document.getElementById("fr");

			sendform.action = "/join";
			sendform.mothod = "post";
			sendform.submit();

		} else {
			$('#checkbox-message').css("color", "#ec6090").text("*필수 항목은 반드시 동의하셔야 합니다.");
		}
	});
});