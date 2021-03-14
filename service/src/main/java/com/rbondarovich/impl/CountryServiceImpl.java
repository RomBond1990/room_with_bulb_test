package com.rbondarovich.impl;

import com.rbondarovich.bean.CountryBean;
import com.rbondarovich.entity.Country;
import com.rbondarovich.interfaces.CountryService;
import com.rbondarovich.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final EntityBeanConverterImpl converter;
    private final CountryRepository countryRepository;

    @Override
    public Iterable<CountryBean> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        List<CountryBean> countryBeans = converter.convertToBeanList(countries, CountryBean.class);

        return countryBeans;
    }

    @Override
    public CountryBean getCountryById(Long id) {
        Country country = countryRepository.getOne(id);
        CountryBean countryBean = converter.convertToBean(country, CountryBean.class);
        return countryBean;
    }

    @Override
    public void saveCountry(CountryBean countryBean) {
        Country country = converter.convertToEntity(countryBean, Country.class);
        countryRepository.save(country);
    }

    @Override
    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
}
