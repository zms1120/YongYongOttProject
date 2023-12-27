(function($) {

	"use strict";

	// Page loading animation
	$(window).on('load', function() {

		$('#js-preloader').addClass('loaded');

	});

	// WOW JS
	$(window).on('load', function() {
		if ($(".wow").length) {
			var wow = new WOW({
				boxClass: 'wow',      // Animated element css class (default is wow)
				animateClass: 'animated', // Animation css class (default is animated)
				offset: 20,         // Distance to the element when triggering the animation (default is 0)
				mobile: true,       // Trigger animations on mobile devices (default is true)
				live: true,       // Act on asynchronously loaded content (default is true)
			});
			wow.init();
		}
	});


// 스크롤 내리ㄹ며 헤더 사라짐

	$(window).scroll(function() {
		var scroll = $(window).scrollTop();
		var box = $('.header-text').height();
		var header = $('header').height();

		if (scroll >= box - header) {
			$("header").addClass("background-header");
		} else {
			$("header").addClass("background-header");
		}
	});


	$('.filters ul li').click(function() {
		$('.filters ul li').removeClass('active');
		$(this).addClass('active');

		var data = $(this).attr('data-filter');
		$grid.isotope({
			filter: data
		})
	});

	var $grid = $(".grid").isotope({
		itemSelector: ".all",
		percentPosition: true,
		masonry: {
			columnWidth: ".all"
		}
	})

	var width = $(window).width();
	$(window).resize(function() {
		if (width > 992 && $(window).width() < 992) {
			location.reload();
		}
		else if (width < 992 && $(window).width() > 992) {
			location.reload();
		}
	})



	$(document).on("click", ".naccs .menu div", function() {
		var numberIndex = $(this).index();

		if (!$(this).is("active")) {
			$(".naccs .menu div").removeClass("active");
			$(".naccs ul li").removeClass("active");

			$(this).addClass("active");
			$(".naccs ul").find("li:eq(" + numberIndex + ")").addClass("active");

			var listItemHeight = $(".naccs ul")
				.find("li:eq(" + numberIndex + ")")
				.innerHeight();
			$(".naccs ul").height(listItemHeight + "px");
		}
	});

	$('.owl-features').owlCarousel({
		items: 5,
		loop: true,
		dots: false,
		nav: true,
		navText: ["<i class='fas fa-chevron-left'></i>", "<i class='fas fa-chevron-right'></i>"],
		autoplay: true,
		margin: 30,
		responsive: {
			0: {
				items: 1
			},
			600: {
				items: 3
			},
			1200: {
				items: 5
			}
		}
	})

	$('.owl-banner').owlCarousel({
		items: 1,
		loop: true,
		margin: 10,
		nav: true,
		navText: ["<div class='nav-button owl-prev'>‹</div>", "<div class='nav-button owl-next'>›</div>"],
		autoplay: true,
		responsive: {
			0: {
				items: 1
			},
		}
	})





	// Menu Dropdown Toggle
	if ($('.menu-trigger').length) {
		$(".menu-trigger").on('click', function() {
			$(this).toggleClass('active');
			$('.header-area .nav').slideToggle(200);
		});
	}


	// Menu elevator animation
	$('.scroll-to-section a[href*=\\#]:not([href=\\#])').on('click', function() {
		if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
			var target = $(this.hash);
			target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
			if (target.length) {
				var width = $(window).width();
				if (width < 991) {
					$('.menu-trigger').removeClass('active');
					$('.header-area .nav').slideUp(200);
				}
				$('html,body').animate({
					scrollTop: (target.offset().top) - 80
				}, 700);
				return false;
			}
		}
	});


	$(document).ready(function() {
		$(document).on("scroll", onScroll);

		//smoothscroll
		$('.scroll-to-section a[href^="#"]').on('click', function(e) {
			e.preventDefault();
			$(document).off("scroll");

			$('.scroll-to-section a').each(function() {
				$(this).removeClass('active');
			})
			$(this).addClass('active');

			var target = this.hash,
				menu = target;
			var target = $(this.hash);
			$('html, body').stop().animate({
				scrollTop: (target.offset().top) - 79
			}, 500, 'swing', function() {
				window.location.hash = target;
				$(document).on("scroll", onScroll);
			});
		});
	});


	function onScroll(event) {
		var scrollPos = $(document).scrollTop();
		$('.nav a').each(function() {
			var currLink = $(this);
			var refElement = $(currLink.attr("href"));
			if (refElement.position().top <= scrollPos && refElement.position().top + refElement.height() > scrollPos) {
				$('.nav ul li a').removeClass("active");
				currLink.addClass("active");
			}
			else {
				currLink.removeClass("active");
			}
		});
	}
	

	// Page loading animation
	$(window).on('load', function() {
		if ($('.cover').length) {
			$('.cover').parallax({
				imageSrc: $('.cover').data('image'),
				zIndex: '1'
			});
		}

		$("#preloader").animate({
			'opacity': '0'
		}, 600, function() {
			setTimeout(function() {
				$("#preloader").css("visibility", "hidden").fadeOut();
			}, 300);
		});
	});



	const dropdownOpener = $('.main-nav ul.nav .has-sub > a');

	// Open/Close Submenus
	if (dropdownOpener.length) {
		dropdownOpener.each(function() {
			var _this = $(this);

			_this.on('tap click', function(e) {
				var thisItemParent = _this.parent('li'),
					thisItemParentSiblingsWithDrop = thisItemParent.siblings('.has-sub');

				if (thisItemParent.hasClass('has-sub')) {
					var submenu = thisItemParent.find('> ul.sub-menu');

					if (submenu.is(':visible')) {
						submenu.slideUp(450, 'easeInOutQuad');
						thisItemParent.removeClass('is-open-sub');
					} else {
						thisItemParent.addClass('is-open-sub');

						if (thisItemParentSiblingsWithDrop.length === 0) {
							thisItemParent.find('.sub-menu').slideUp(400, 'easeInOutQuad', function() {
								submenu.slideDown(250, 'easeInOutQuad');
							});
						} else {
							thisItemParent.siblings().removeClass('is-open-sub').find('.sub-menu').slideUp(250, 'easeInOutQuad', function() {
								submenu.slideDown(250, 'easeInOutQuad');
							});
						}
					}
				}

				e.preventDefault();
			});
		});
	}





})(window.jQuery);


// 전화번호 마스킹 함수
function maskPhoneNumber(phoneNumber) {
	var parts = phoneNumber.split('-');
	if (parts.length === 3 && parts[1].length === 4) {
		parts[1] = "*".repeat(parts[1].length);
		return parts.join("-");
	}
	return phoneNumber; // 잘못된 형식의 번호인 경우 그대로 반환
}

// 전화번호 요소 선택 후 마스킹 처리
var phoneNumberElement = document.getElementById('phone_num');
if (phoneNumberElement) {
	phoneNumberElement.innerHTML = maskPhoneNumber(phoneNumberElement.innerHTML);
}


// jQuery를 사용하여 div를 토글 형식
$(document).ready(function() {
    var maxVisibleEpisodes = 8; // 보이는 최대 div의 개수
    var $episodes = $('.episode');

    // 처음에는 일정 개수 이후의 div를 숨김
    $episodes.slice(maxVisibleEpisodes).hide();
    $('#close').hide();

    // 버튼을 클릭하면 숨겨진 div를 보이게 하거나 숨기게 하기
    $('#showmore').on('click', function() {
        // 'showmore' 버튼을 클릭했을 때
        $episodes.slice(maxVisibleEpisodes).toggle(); // 일정 개수 이후의 div를 토글
        $(this).hide(); // 'showmore' 버튼 숨김
        $('#close').show(); // 'close' 버튼 보임
    });

    $('#close').on('click', function() {
        // 'close' 버튼을 클릭했을 때
        $episodes.slice(maxVisibleEpisodes).hide(); // 일정 개수 이후의 div를 숨김
        $(this).hide(); // 'close' 버튼 숨김
        $('#showmore').show(); // 'showmore' 버튼 보임
    });
});

// 비밀번호 입력시 비밀번호 체크하는 input 나타남
$('#password').on('input', function() {
    var passwordInput = $(this).val().trim();
    var $passwordCkInput = $('#password_ck');

    if (passwordInput.length > 0) {
        $passwordCkInput.parent().show(); // password_ck input을 보이게 함
    } else {
        $passwordCkInput.parent().hide(); // password_ck input을 숨김
    }
});

// prev를 누를 때마다 이전 div로 넘어감
$(document).ready(function() {
	$('#prev-to-step1').on('click', function(e) {
		e.preventDefault();
		$('.step2').hide();
		$('.step1').show();
	});
	
	$('#prev-to-step2').on('click', function(e) {
		e.preventDefault();
		$('.step3').hide();
		$('.step2').show();
	});
	
	$('#prev-to-step3').on('click', function(e) {
		e.preventDefault();
		$('.step4').hide();
		$('.step3').show();
	});
	
	$('#prev-to-step4').on('click', function(e) {
		e.preventDefault();
		$('.step5').hide();
		$('.step4').show();
	});
	
});

// checked 이벤트 처리
$(document).ready(function() {
	$('#age-restriction').change(function() {
		if (this.checked) {
			$('.age-restriction').css('background-color', '#1f2122');
		} else {
			$('.age-restriction').css('background-color', ''); // 기본값으로 돌리거나 다른 배경색 지정 가능
		}
	});
	
	$('#service').change(function() {
		if (this.checked) {
			$('.service').css('background-color', '#1f2122');
		} else {
			$('.service').css('background-color', ''); // 기본값으로 돌리거나 다른 배경색 지정 가능
		}
	});
	
	$('#member_privacy').change(function() {
		if (this.checked) {
			$('.member_privacy').css('background-color', '#1f2122');
		} else {
			$('.member_privacy').css('background-color', ''); // 기본값으로 돌리거나 다른 배경색 지정 가능
		}
	});
	
	$('#member_payment').change(function() {
		if (this.checked) {
			$('.member_payment').css('background-color', '#1f2122');
		} else {
			$('.member_payment').css('background-color', ''); // 기본값으로 돌리거나 다른 배경색 지정 가능
		}
	});
	
	$('#member_event').change(function() {
		if (this.checked) {
			$('.member_event').css('background-color', '#1f2122');
		} else {
			$('.member_event').css('background-color', ''); // 기본값으로 돌리거나 다른 배경색 지정 가능
		}
	});
	
	$('#member_ad').change(function() {
		if (this.checked) {
			$('.member_ad').css('background-color', '#1f2122');
		} else {
			$('.member_ad').css('background-color', ''); // 기본값으로 돌리거나 다른 배경색 지정 가능
		}
	});
	
	$('#member_target').change(function() {
		if (this.checked) {
			$('.member_target').css('background-color', '#1f2122');
		} else {
			$('.member_target').css('background-color', ''); // 기본값으로 돌리거나 다른 배경색 지정 가능
		}
	});
	
});

$(document).ready(function() {
	
	$('#guest-btn').on('click', function(e) {
		e.preventDefault();
		var position = $('#position').val();
		
		if(position === "" || position !== 'GUEST'){
			 $(this).css({
	            'border-color': '#ec6090',
	            'background-color': '#ec6090',
	            'color': '#f7f7f7'
        	});
        	
        	$('#premium-btn, #basic-btn').css({
	            'border-color': '',
	            'background-color': '',
	            'color': ''
	        });
        	
			$('.guest').css('color', '#9ADBE8');
			$('.basic').css('color', '#f7f7f7');
			$('.premium').css('color', '#f7f7f7');
			
			$('#position').val('GUEST');
		}
	});
	
	$('#basic-btn').on('click', function(e) {
		e.preventDefault();
		var position = $('#position').val();
		
		if(position === "" || position !== 'BASIC'){
			$(this).css({
	            'border-color': '#ec6090',
	            'background-color': '#ec6090',
	            'color': '#f7f7f7'
        	});
        	$('#guest-btn, #premium-btn').css({
	            'border-color': '',
	            'background-color': '',
	            'color': ''
	        });
			$('.basic').css('color', '#9ADBE8');
			$('.guest').css('color', '#f7f7f7');
			$('.premium').css('color', '#f7f7f7');
			$('#position').val('BASIC');
		}
	});
	
	$('#premium-btn').on('click', function(e) {
		e.preventDefault();
		var position = $('#position').val();
		
		if(position === "" || position !== 'PREMIUM'){
			$(this).css({
	            'border-color': '#ec6090',
	            'background-color': '#ec6090',
	            'color': '#f7f7f7'
        	});
        	$('#guest-btn, #basic-btn').css({
	            'border-color': '',
	            'background-color': '',
	            'color': ''
	        });
			
			$('.premium').css('color', '#9ADBE8');
			$('.guest').css('color', '#f7f7f7');
			$('.basic').css('color', '#f7f7f7');
			$('#position').val('PREMIUM');
		}
	});
});

function goMovieVideo(movie, member){
	var rating = movie.rating;
	var videoPath = movie.video_path;
	var member_age = member.age;
	var position = member.position;
	
    if(position == null){
		 // 로그인 하지 않은 사람
		alert("로그인 후 이용해 주세요.");
		window.location.href = "/login";
	} else if(position != 'PREMIUM' && position != 'ADMIN'){
		// 포지션 확인
		alert("이 이용권은 해당 컨텐츠를 사용할 수 없습니다.");
		return;
	} else if(rating == '18' && member_age < 18){
		alert("컨텐츠를 이용할 수 있는 연령이 아닙니다.");
		return;
	} else if(rating == '15' && member_age < 15){
		alert("컨텐츠를 이용할 수 있는 연령이 아닙니다.");
		return;
	} else if(rating == '12' && member_age < 12){
		alert("컨텐츠를 이용할 수 있는 연령이 아닙니다.");
		return;
	} else {
		window.open("https://www.youtube.com/watch?v=" + videoPath);
	}
	
}

function goEpiVideo(videoPath, rating, member){
	var member_age = member.age;
	var position = member.position;
	
    if(position == null){
		 // 로그인 하지 않은 사람
		alert("로그인 후 이용해 주세요.");
		window.location.href = "/login";
	} else if(rating == '18' && member_age < 18){
		alert("컨텐츠를 이용할 수 있는 연령이 아닙니다.");
		return;
	} else if(rating == '15' && member_age < 15){
		alert("컨텐츠를 이용할 수 있는 연령이 아닙니다.");
		return;
	} else if(rating == '12' && member_age < 12){
		alert("컨텐츠를 이용할 수 있는 연령이 아닙니다.");
		return;
	} else {
		window.open("https://www.youtube.com/watch?v=" + videoPath);
	}
	
}