package com.example.demo.country;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;        // <-- correcto
import static org.mockito.ArgumentMatchers.anyString;  // <-- correcto
import static org.mockito.Mockito.times;               // <-- correcto
import static org.mockito.Mockito.verify;              // <-- correcto
import static org.mockito.Mockito.when;                // <-- correcto

@ExtendWith(MockitoExtension.class)
class CountryServiceImplTest {

    @Mock
    CountryRepository countryRepository;

    @InjectMocks
    CountryServiceImpl countryService;

    @Test
    void findAllCountriesSorted_devuelveListaOrdenada() {
        CountryJpa c = new CountryJpa(); c.setId(1L); c.setName("Spain");
        when(countryRepository.findAll(any(Sort.class))).thenReturn(List.of(c));

        var res = countryService.findAllCountriesSorted();

        assertEquals(1, res.size());
        assertEquals("Spain", res.get(0).getName());
        verify(countryRepository, times(1)).findAll(any(Sort.class));
    }

    @Test
    void findCountryByName_lanzaExcepcionSiNoExiste() {
        when(countryRepository.findByName(anyString())).thenReturn(Optional.empty());
        assertThrows(CountryNotFoundException.class,
                () -> countryService.findCountryByName("Nowhere"));
    }

    @Test
    void capturaSortAscPorName() {
        var captor = ArgumentCaptor.forClass(Sort.class);
        when(countryRepository.findAll(captor.capture())).thenReturn(List.of());

        countryService.findAllCountriesSorted();

        assertEquals(Sort.by(Sort.Direction.ASC, "name"), captor.getValue());
    }
}
