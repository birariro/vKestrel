package com.birariro.vkestrel.events;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.birariro.vkestrel.adapter.batch.step.event.LibraryStateSwitchEvent;
import com.birariro.vkestrel.adapter.message.event.Events;

@SpringBootTest
public class MultEventsTest {

	@Test
	@DisplayName("하나의 이벤트를 두군데에서 구독시 둘다 발생하는지 테스트")
	public void test(){
		Events.raise(LibraryStateSwitchEvent.active("test name"));
	}
}
