package com.sky.service;

import com.sky.entity.AddressBook;

import java.util.List;

/**
 * @version 1.0
 * @Author Imak
 * @Date 2024/8/12 13:47
 * @注释
 */
public interface AddressBookService {
    List<AddressBook> list(AddressBook addressBook);

    void save(AddressBook addressBook);

    AddressBook getById(Long id);

    void update(AddressBook addressBook);

    void setDefault(AddressBook addressBook);

    void deleteById(Long id);
}
