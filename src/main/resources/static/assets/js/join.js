
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
			pwd_checker = 0;
		} else {
			$('#pwd-message').css("color", "aquamarine").text("확인되었습니다.");
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


// next를 누를 때마다 다른 div로 넘어감
$(document).ready(function() {
	$('#next-to-step2').on('click', function(e) {
		e.preventDefault();
		//alert("id-checker = "  + id_checker + " pwd-checker = " + pwd_checker + " pwdck-checker = " + pwdck_checker)
		
		if(id_checker == 1 
			&& pwd_checker == 1
			&& pwdck_checker == 1){
				$('#email').val($('#id').val());
				alert("id: " + $('#id').val() + " email: " + $('#email').val());
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
		var name = $('#name').val();
		var phone_number = $('#phone_number').val();
		var age = $('#age').val();
		
		//alert(name + ", " + phone_number + ", " + age);
		
		if(name !== "" && phone_number !== "" && age !== ""){
			$('.step3').hide();
			$('.step4').show();
			calcRegDate();
		} else {
			$('#step3-message').css("color", "#ec6090").text("*입력사항을 확인해주세요.");
		}
	});
	
	$('#next-to-step5').on('click', function(e) {
		e.preventDefault(); 
		
		if($('#position').val() !== ""){
			dateCalculator();
			//alert("regDate: " +  $('#reg_date').val() + " renewDate: " +  $('#renew_date').val() + " endDate: " +  $('#end_date').val());
			$('.step4').hide();
			$('.step5').show();
		} else {
			$('#membership-message').css("color", "#ec6090").text("*이용권은 반드시 선택하셔야 합니다.");
		}
	});
	
	$('#submit-button').on('click', function(e) {
		e.preventDefault();
		
		if (!$('#age-restriction').prop('checked')
	        || !$('#service').prop('checked')
	        || !$('#member_privacy').prop('checked')
	        || !$('#member_payment').prop('checked')) {
			$('#checkbox-message').css("color", "#ec6090").text("*필수 항목은 반드시 동의하셔야 합니다.");

		} else {
			var phone = change_number($('#phone_number').val());
			$('#phone_number').val(phone);
			//alert("before: " +$('#phone_number').val() + " after: " + phone);
			
			save();
		}
	});
	
});

function calcRegDate() {
   var today = new Date();

   var reg_dateInput = document.getElementById("reg_date");
   reg_dateInput.value = formatDate(today);
}

function dateCalculator() {
   // 변경일 가져오기
   var reg_dateString = document.getElementById("reg_date").value;
   
   // 문자열을 Date 객체로 변환
   var reg_dateObj = new Date(reg_dateString);
   var renewDateObj = new Date(reg_dateObj);
   var endDateObj = new Date(reg_dateObj);

   if (reg_dateObj) {
      var position = document.getElementById("position").value;

      // 이용권 종료날짜 계산 (방문자는 8일, 나머지는 31일)
      if (position === "GUEST") {
         endDateObj.setDate(renewDateObj.getDate() + 7);
         
      } else {
         endDateObj.setDate(renewDateObj.getDate() + 30);
         
      }
      // 각 입력 날짜 설정
      var renewDateElement = document.getElementById("renew_date");
      var endDateElement = document.getElementById("end_date");
      
      // 각 입력 날짜 설정
      renewDateElement.value = formatDate(renewDateObj);
      endDateElement.value = formatDate(endDateObj);
   }
}

// 날짜를 "yyyy-MM-dd" 형식으로 변환
function formatDate(date) {

   var year = date.getFullYear();
   var month = (date.getMonth() + 1).toString().padStart(2, '0');
   var day = date.getDate().toString().padStart(2, '0');
   return year + "-" + month + "-" + day;
}
	
function change_number(phone_number) {
    let inputValue = phone_number.replace(/[^0-9]/g, ''); // 입력된 값 중 숫자만 남기기

    if (inputValue.length <= 11) {
        inputValue = inputValue.replace(/(\d{0,3})(\d{0,4})(\d{0,4})/, '$1-$2-$3'); // 전화번호 형식에 맞게 변환
    } else if (inputValue.length > 11) {
        inputValue = inputValue.substring(0, 11).replace(/(\d{0,3})(\d{0,4})(\d{0,4})/, '$1-$2-$3'); 
    }
    
    return inputValue;
}

function save() {
	var sendform = document.getElementById("join");

	if (sendform) {
		sendform.action = "/join";
		sendform.method = "post";
		sendform.submit();
	} else {
		alert("폼을 찾을 수 없습니다.");
	}
}

function goWindow(url){
	window.open(url);
}