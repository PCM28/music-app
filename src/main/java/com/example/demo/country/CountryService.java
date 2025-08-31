package com.example.demo.country;

import java.util.List;

public interface CountryService {
    List<CountryJpa> findAllCountriesSorted();
    CountryJpa findCountryByName(String name);
}
