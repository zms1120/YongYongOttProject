
// 아이디 중복 확인
function checkDuplicateId() {
	var id = document.getElementById("id").value;
	var sendform = document.getElementById("fr");

	sendform.action = "/check_find_id?id=" + id;
	sendform.mothod = "GET";
	sendform.submit();
}
