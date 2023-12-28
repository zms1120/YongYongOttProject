// 커뮤니티 보드 작성시 =====================================================================
   // 저장하기전 빈칸
   function board_save() {
      var b_category = document.getElementById("b_category");
      var title = document.getElementById("title");
      var content = document.getElementById("content");  
    console.log("update 성공");
        if (b_category.value == "" || b_category.length == 0) {
         alert("카테고리를 선택해주세용.")
         b_category.focus();
         return false;
      } else if (title.value == "" || title.length == 0) {
         alert("제목을 입력해주세용.")
         title.focus();
         return false;
      }else if (content.value == "" || content.length == 0) {
         alert("내용을 작성해주세용.")
         content.focus();
         return false;
      } else {
         return true;
      }
   }
   // 좋아요 버튼 누르면 카운드 =======================================================
function likePlus() {
    var likecount = document.getElementById("like_count");
    var likeButton = document.getElementById("like_button");
    var currentCount = parseInt(likecount.innerText);

    // 좋아요 수 증가
    likecount.innerText = currentCount + 1;

    // 버튼 비활성화
    likeButton.disabled = true;

    // 좋아요 숫자 +1하기
    var boardSeq = document.getElementById("b_seq").value;
    console.log(boardSeq); // 확인용 출력
    sendLikeRequest(boardSeq);
}


function sendLikeRequest(boardSeq) {
    var boardSeqLong = Number(boardSeq);

    // HTML 메타 태그에서 CSRF 토큰을 가져옵니다.
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");

    $.ajax({
        url: '/like',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'text',
        data: JSON.stringify({ b_seq: boardSeqLong }),
        beforeSend: function (xhr) {
            // AJAX 요청 헤더에 CSRF 토큰을 추가합니다.
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (response) {
            console.log('좋아요 수 증가 요청 성공');
            console.log(response);
        },
        error: function () {
            console.log('좋아요 수 증가 요청 실패');
        }
    });
}

