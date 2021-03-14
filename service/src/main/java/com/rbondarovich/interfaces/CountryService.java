package com.rbondarovich.interfaces;

import com.rbondarovich.bean.CountryBean;

public interface CountryService {

    Iterable<CountryBean> getAllCountries();

    CountryBean getCountryById (Long id);

    void saveCountry (CountryBean countryBean);

    void deleteCountry (Long id);
}
