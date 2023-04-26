package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.entity.Finances;

import java.util.List;

public interface FinancesService {
    void save(Finances finances);
    List<Finances> getAll();
}
