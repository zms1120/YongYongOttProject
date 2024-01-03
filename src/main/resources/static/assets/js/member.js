// 날짜관련 js ============================================================

function calculateRegDate() { // 페이지 로드되면 바로 가입일은 현재 날짜로 작성됨
   var today = new Date();

   var change_dateInput = document.getElementById("change_date");
   change_dateInput.value = formatDate(today);
}

// 날짜를 "yyyy-MM-dd" 형식으로 변환
function formatDate(date) {

   var year = date.getFullYear();
   var month = (date.getMonth() + 1).toString().padStart(2, '0');
   var day = date.getDate().toString().padStart(2, '0');
   return year + "-" + month + "-" + day;
}
//===============================================================================

// change.html 관련 js ============================================================       
// 회원 버튼 누르면 자동으로 들어감 
function setMembership(position) {
   var positionSelect = document.getElementById('positionSelect');
   var options = positionSelect.options;
   for (var i = 0; i < options.length; i++) {
      if (options[i].value === position) {
         positionSelect.value = position;
         break;
      }
   }

   // 누른 버튼에 따라 changeDate 함수 호출
   changeDate();
}

function changeDate() {
   // 변경일 가져오기
   var change_dateString = document.getElementById("change_date").value;
   
   // 문자열을 Date 객체로 변환
   var change_dateObj = new Date(change_dateString);

   // 날짜를 "yyyy-MM-dd" 형식으로 변환
   var renewDateObj = new Date(change_dateObj);
   var endDateObj = new Date(change_dateObj);

   if (change_dateObj) {
      var position = document.getElementById("positionSelect").value;

      // 이용권 종료날짜 계산 (방문자는 8일, 나머지는 31일)
      if (position === "GUEST") {
         endDateObj.setDate(change_dateObj.getDate() + 7);
         
      } else {
         endDateObj.setDate(change_dateObj.getDate() + 30);
         
      }
      // 각 입력 날짜 설정
      var renewDateElement = document.getElementById("renew_date");
      var endDateElement = document.getElementById("end_date");
      
      // 각 입력 날짜 설정
      renewDateElement.value = formatDate(renewDateObj);
      endDateElement.value = formatDate(endDateObj);
   }
}

//===============================================================================
// 회원정보 수정=====================================================================
   // 저장하기전 빈칸(수정에서는 비번, 이름, 핸드폰번호 변경 가능)
   function modify_save() {
      var password = document.getElementById("password");
      var name = document.getElementById("name");
      var phone_number = document.getElementById("phone_number");
      //console.log("update 성공"+phone_number);    
    
        if (name.value == "" || name.length == 0) {
         alert("이름 입력해주세요.")
         name.focus();
         return false;
      } else if (password.value == "" || password.length == 0) {
         alert("비밀번호를 입력해주세요.")
         password.focus();
         return false;
      } else if (!modify_pwd_check()) {
        // modify_pwd_check 함수가 false를 반환하면 폼 전송을 막음
        password.focus();
        return false;
      } else if (phone_number.value == "" || phone_number.length == 0) {
         alert("핸드폰 번호를 입력해주세요.")
         phone_number.focus();
         return false;
      } else {
         return true;
      }
   }
   
   
   // 비밀번호 확인
function modify_pwd_check() {
	$('#password').on('focusout', function(e) {
		var password = $('#password').val();
		// 숫자, 특수문자 각각을 포함하는지 확인
		const numberRegex = /[0-9]/;
		const specialCharRegex = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/;
		
		// 비밀번호 길이가 8-20자인지 확인
		if (password == "") {
			return false;
		} else if (
			password.length < 8 || password.length > 20 ||
			!numberRegex.test(password) ||
			!specialCharRegex.test(password)
		) {
			$('#pwd-message').css("color", "#ec6090").text("비밀번호 형식에 맞게 입력해주세요");
			e.preventDefault();
			return false;
		} else {
			$('#pwd-message').css("color", "aquamarine").text("확인되었습니다.");
			return true;
		}
	});
}

$('#password').on('focus', function() {
	$('#pwd-message').css("color", "#f7f7f7").text("비밀번호는 8-20자 이내로 영문 대소문자, 숫자, 특수문자를 반드시 하나씩 포함하여 입력해주세요.");
});



// modify_save 함수가 true를 반환하면 폼이 전송됨
$('#modify').submit(function() {
	return modify_save();
});