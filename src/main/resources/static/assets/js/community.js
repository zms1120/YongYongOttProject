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