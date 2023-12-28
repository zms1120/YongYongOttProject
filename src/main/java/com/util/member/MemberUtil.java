package com.util.member;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.ott.entity.Member;

@Component
public class MemberUtil {
	
	private Date today = new Date();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public Member checkPosition(Member user) {
		
		if(user.getPosition().equals("ADMIN")) {
			return user;
		} else if(user.getPosition().equals("GUEST")) {
			return guestcheck(user, today);
		} else {
			return defaultCheck(user, today);
		}
	}
	
	private Member guestcheck(Member user, Date today) {
		String formatToday = dateFormat.format(today);
        String userEndDate = dateFormat.format(user.getEnd_date());
        
        System.out.println("today: " + formatToday);
		System.out.println("user.endDate: " + user.getEnd_date());
        
		if(formatToday.equals(userEndDate)) { // 베이직으로 자동 갱신
			System.out.println("guest 갱신필요");
			
			user.setPosition("BASIC");
			user.setRenew_date(today);
			
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(today);
	        calendar.add(Calendar.MONTH, 1);

	        user.setEnd_date(calendar.getTime());
	        
		}
		
		return user;
	}
	
	private Member defaultCheck(Member user, Date today) {
		if(user.getEnd_date().equals(today)) { // 갱신
			user.setRenew_date(today);
			
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(today);
	        calendar.add(Calendar.MONTH, 1);

	        user.setEnd_date(calendar.getTime());
	        
		}
		
		return user;
	}
	
}
