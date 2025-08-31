package com.example.demo.country;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<CountryJpa> findAllCountriesSorted() {
        // Igual que en el PDF: ordenar ASC por name
        return countryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public CountryJpa findCountryByName(String name) {
        return countryRepository.findByName(name)
                .orElseThrow(() -> new CountryNotFoundException("Country not found: " + name));
    }
}