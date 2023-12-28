// 어드민 등록  =====================================================================
//영화 관련 등록 및 수정
function movie_save() {

	var movie_code = document.getElementById("movie_code");
	var genre = document.getElementById("genre");
	var m_title_ko = document.getElementById("m_title_ko");
	var director = document.getElementById("director");
	var nation = document.getElementById("nation");
	var open_year = document.getElementById("open_year");
	var running_time = document.getElementById("running_time");
	var rating = document.getElementById("rating");
	var keyword = document.getElementById("keyword");
	var cast = document.getElementById("cast");
	var description = document.getElementById("description");
	var video_path = document.getElementById("video_path");
	var image = document.getElementById("image");
	var banner = document.getElementById("banner");

	if (movie_code.value == "" || movie_code.length == 0) {
		console.log("영화 코드를 입력해주세요.");  // 조건문에 진입했음을 로그에 남깁니다.
		alert("영화 코드를 입력해주세요.");
		movie_code.focus();
		return false;
	} else if (genre.value == "" || genre.length == 0) {
		console.log("장르를 입력해주세요.");
		alert("장르를 입력해주세요.")
		genre.focus();
		return false;
	} else if (m_title_ko.value == "" || m_title_ko.length == 0) {
		alert("제목을 입력해주세요.")
		m_title_ko.focus();
		return false;
	} else if (director.value == "" || director.length == 0) {
		alert("감독을 입력해주세요.")
		director.focus();
		return false;
	} else if (nation.value == "" || nation.length == 0) {
		alert("국가를 입력해주세요.")
		nation.focus();
		return false;
	} else if (open_year.value == "" || open_year.length == 0) {
		alert("개봉년도를 입력해주세요.")
		open_year.focus();
		return false;
	} else if (running_time.value == "" || running_time.length == 0) {
		alert("상영 시간을 입력해주세요.")
		running_time.focus();
		return false;
	} else if (rating.value == "" || rating.length == 0) {
		alert("등급을 입력해주세요.")
		rating.focus();
		return false;
	} else if (keyword.value == "" || keyword.length == 0) {
		alert("키워드를 입력해주세요.")
		keyword.focus();
		return false;
	} else if (cast.value == "" || cast.length == 0) {
		alert("출연진을 입력해주세요.")
		cast.focus();
		return false;
	} else if (description.value == "" || description.length == 0) {
		alert("줄거리를 입력해주세요.")
		description.focus();
		return false;
	} else if (video_path.value == "" || video_path.length == 0) {
		alert("영상주소를 입력해주세요.")
		video_path.focus();
		return false;
	} else if (image.value == "" || image.length == 0) {
		alert("이미지를 넣어주세요.")
		image.focus();
		return false;
	} else if (banner.value == "" || banner.length == 0) {
		alert("배너를 넣어주세요.")
		banner.focus();
		return false;
	} else {
		return true;
	}
}

function tv_save() {
	var  p_category= document.getElementById("p_category");
	var  p_title= document.getElementById("p_title");
	var  p_director= document.getElementById("p_director");
	var  p_writer= document.getElementById("p_writer");
	var  airing_period= document.getElementById("airing_period");
	var  p_board_casting= document.getElementById("p_board_casting");
	var  nation= document.getElementById("nation");
	var  rating= document.getElementById("rating");
	var  p_cast= document.getElementById("p_cast");
	var  description= document.getElementById("description");
	var  image= document.getElementById("image");
	

	if (p_category.value == "" || p_category.length == 0) {
		alert("를 입력해주세요.");
		p_category.focus();
		return false;
	} else if (p_title.value == "" || p_title.length == 0) {
		alert("제목을 입력해주세요.")
		p_title.focus();
		return false;
	} else if (p_director.value == "" || p_director.length == 0) {
		alert("감독을 입력해주세요.")
		p_director.focus();
		return false;
	} else if (p_writer.value == "" || p_writer.length == 0) {
		alert("작가를 입력해주세요.")
		p_writer.focus();
		return false;
	} else if (airing_period.value == "" || airing_period.length == 0) {
		alert("방영기간을 입력해주세요.")
		airing_period.focus();
		return false;
	} else if (p_board_casting.value == "" || p_board_casting.length == 0) {
		alert("방송사를 입력해주세요.")
		p_board_casting.focus();
		return false;
	} else if (nation.value == "" || nation.length == 0) {
		alert("국가를 입력해주세요.")
		nation.focus();
		return false;
	} else if (rating.value == "" || rating.length == 0) {
		alert("등급를 입력해주세요.")
		rating.focus();
		return false;
	} else if (p_cast.value == "" || p_cast.length == 0) {
		alert("출연진를 입력해주세요.")
		p_cast.focus();
		return false;
	} else if (description.value == "" || description.length == 0) {
		alert("줄거리를 입력해주세요.")
		description.focus();
		return false;
	} else if (image.value == "" || image.length == 0) {
		alert("이미지를 입력해주세요.")
		image.focus();
		return false;
	} else if (banner.value == "" || banner.length == 0) {
		alert("배너를 입력해주세요.")
		banner.focus();
		return false;
	} else {
		return true;
	}
}
function episode_save() {
	var  episode_num= document.getElementById("episode_num");
	var  ep_title= document.getElementById("ep_title");
	var  airing_date= document.getElementById("airing_date");
	var  running_time= document.getElementById("running_time");
	var  description= document.getElementById("description");
	var  video_path= document.getElementById("video_path");
		
	if (episode_num.value == "" || episode_num.length == 0) {
		alert("제목을 입력해주세요.");
		episode_num.focus();
		return false;
	} else if (ep_title.value == "" || ep_title.length == 0) {
		alert("회차 제목을 입력해주세요.")
		ep_title.focus();
		return false;
	} else if (airing_date.value == "" || airing_date.length == 0) {
		alert("방영날짜를 입력해주세요.")
		airing_date.focus();
		return false;
	} else if (running_time.value == "" || running_time.length == 0) {
		alert("방영시간을 입력해주세요.")
		running_time.focus();
		return false;
	} else if (description.value == "" || description.length == 0) {
		alert("줄거리를 입력해주세요.")
		description.focus();
		return false;
	} else if (video_path.value == "" || video_path.length == 0) {
		alert("영상 주소을 입력해주세요.")
		video_path.focus();
		return false;
	} else {
		return true;
	}
}

