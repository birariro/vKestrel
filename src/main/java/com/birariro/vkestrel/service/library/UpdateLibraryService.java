package com.birariro.vkestrel.service.library;

import java.util.Optional;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.birariro.vkestrel.adapter.batch.step.event.LibraryStateSwitchEvent;
import com.birariro.vkestrel.adapter.persistence.library.Library;
import com.birariro.vkestrel.adapter.persistence.library.LibraryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UpdateLibraryService {
	private final LibraryRepository libraryRepository;

	@EventListener(LibraryStateSwitchEvent.class)
	private void updateState(LibraryStateSwitchEvent event){
		log.info("[UpdateLibraryService] update state");
		Optional<Library> firstByName = libraryRepository.findFirstByName(event.getName());

		if(! firstByName.isPresent()) return;


		if(event.isState()){
			if(event.isError()){
				firstByName.get().error();
			}
			firstByName.get().active();
		}else{
			firstByName.get().inActive();
		}

		libraryRepository.save(firstByName.get());

	}
}
