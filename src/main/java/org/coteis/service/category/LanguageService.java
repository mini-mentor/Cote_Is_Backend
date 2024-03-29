package org.coteis.service.category;

import lombok.RequiredArgsConstructor;
import org.coteis.domain.category.Algorithm;
import org.coteis.domain.category.Language;
import org.coteis.repository.category.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    // read
    public List<Language> findALl(){
        return languageRepository.findAll();
    }

    public Language findById(Integer id) {
        return languageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }
}
